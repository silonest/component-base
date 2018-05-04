package pers.silonest.component.http;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pers.silonest.component.util.JsonUtils;
import pers.silonest.component.util.StringUtils;

public abstract class AbstractHttpRequest implements WebRequest {

  final static Charset UTF_8 = Charset.forName("UTF-8");
  static PoolingHttpClientConnectionManager CONN_MANAGER;
  private static int MAX_CONNECTION_NUM = 400;
  private static int MAX_PER_ROUTE = 80;

  CloseableHttpClient httpClient;

  static {
    try {
      SSLContextBuilder sslContextBuilder = new SSLContextBuilder();
      sslContextBuilder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
      SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
      Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", socketFactory)
          .register("http", new PlainConnectionSocketFactory()).build();
      CONN_MANAGER = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
      CONN_MANAGER.setMaxTotal(MAX_CONNECTION_NUM);
      CONN_MANAGER.setDefaultMaxPerRoute(MAX_PER_ROUTE);
    } catch (Exception e) {
      CONN_MANAGER = null;
    }
  }

  void initHttpClient() {

  }

  void initHttpsClient(String clientPath, String clientPwd, String jksPath, String jksPwd) {

  }

  @Override
  public <T> WebResponse<String> doGet(String url, Map<String, T> params) {
    return doGet(url, params, UTF_8);
  }

  @Override
  public <T> WebResponse<String> doGet(String url, Map<String, T> params, Charset charset) {
    return doGet(url, null, params, charset);
  }

  @Override
  public <T> WebResponse<String> doGet(String url, Map<String, String> header, Map<String, T> params) {
    return doGet(url, header, params, UTF_8);
  }

  @Override
  public <T> WebResponse<String> doGet(String url, Map<String, String> header, Map<String, T> params, Charset charset) {
    if (StringUtils.isEmpty(url)) {
      throw new IllegalArgumentException("Unexpected url.");
    }
    try {
      if (params != null && !params.isEmpty()) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, T> entry : params.entrySet()) {
          String value = (String) entry.getValue();
          if (value != null) {
            pairs.add(new BasicNameValuePair(entry.getKey(), value));
          }
        }
        url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
      }
      HttpGet httpGet = new HttpGet(new URI(url));
      if (header != null && !header.isEmpty()) {
        for (Entry<String, String> entry : header.entrySet()) {
          String name = entry.getKey().trim();
          String value = entry.getValue().trim();
          if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
            httpGet.setHeader(name, value);
          }
        }
      }
      CloseableHttpResponse response = this.httpClient.execute(httpGet);
      if (response == null) {
        throw new ConnectException("No response.");
      }
      int httpStatusCode = response.getStatusLine().getStatusCode();
      HttpEntity entity = response.getEntity();
      String content = null;
      if (entity != null) {
        content = EntityUtils.toString(entity, "utf-8");
      }
      EntityUtils.consume(entity);
      response.close();
      WebResponse<String> result = new WebResponse<String>(httpStatusCode, content);
      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new IllegalArgumentException(ex);
    }
  }

  @Override
  public <T> WebResponse<String> doPost(String url, Map<String, T> params) {
    return doPost(url, null, params, UTF_8, UTF_8, RequestBodyType.APPLICATION_JSON);
  }

  @Override
  public <T> WebResponse<String> doPost(String url, Map<String, String> header, Map<String, T> params) {
    return doPost(url, header, params, UTF_8, UTF_8, RequestBodyType.APPLICATION_JSON);
  }

  @Override
  public <T> WebResponse<String> doPost(String url, Map<String, String> header, Map<String, T> params, RequestBodyType requestBodyType) {
    return doPost(url, header, params, UTF_8, UTF_8, requestBodyType);
  }

  @Override
  public <T> WebResponse<String> doPost(String url, Map<String, String> header, Map<String, T> params, Charset requestCharset,
      Charset responseCharset, RequestBodyType requestBodyType) {
    HttpPost httpPost = new HttpPost();
    CloseableHttpResponse httpResponse = executeHttpClient(httpPost, url, header, params, requestCharset, requestBodyType);
    int httpStatusCode = httpResponse.getStatusLine().getStatusCode();
    HttpEntity entity = httpResponse.getEntity();
    try {
      String content = EntityUtils.toString(entity, responseCharset);
      WebResponse<String> result = new WebResponse<String>(httpStatusCode, content);
      return result;
    } catch (ParseException e) {
      throw new UnknownResponseException(e);
    } catch (IOException e) {
      throw new UnknownResponseException(e);
    }
  }

  @Override
  public <T> WebResponse<String> doPut(String url, Map<String, T> params) {
    return doPut(url, params, UTF_8);
  }

  @Override
  public <T> WebResponse<String> doPut(String url, Map<String, T> params, Charset charset) {
    return doPut(url, null, params, charset);
  }

  @Override
  public <T> WebResponse<String> doPut(String url, Map<String, String> header, Map<String, T> params) {
    return doPut(url, header, params, UTF_8);
  }

  @Override
  public <T> WebResponse<String> doPut(String url, Map<String, String> header, Map<String, T> params, Charset charset) {
    return null;
  }

  @Override
  public <T> WebResponse<String> doDelete(String url, Map<String, T> params) {
    return doDelete(url, params, UTF_8);
  }

  @Override
  public <T> WebResponse<String> doDelete(String url, Map<String, T> params, Charset charset) {
    return doDelete(url, null, params, charset);
  }

  @Override
  public <T> WebResponse<String> doDelete(String url, Map<String, String> header, Map<String, T> params) {
    return doDelete(url, header, params, UTF_8);
  }

  @Override
  public <T> WebResponse<String> doDelete(String url, Map<String, String> header, Map<String, T> params, Charset charset) {
    return null;
  }

  private Header[] buildHeaders(Map<String, String> header) {
    List<Header> resultList = new ArrayList<Header>();
    if (header != null && !header.isEmpty()) {
      for (Entry<String, String> entry : header.entrySet()) {
        String name = entry.getKey().trim();
        String value = entry.getValue().trim();
        if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
          resultList.add(new BasicHeader(name, value));
        }
      }
    }
    return resultList.toArray(new Header[resultList.size()]);
  }

  private <T> HttpEntity buildHttpEntity(Map<String, T> params, Charset charset, RequestBodyType bodyType) {
    switch (bodyType) {
      case APPLICATION_JSON:
        return new StringEntity(JsonUtils.toJson(params), ContentType.APPLICATION_JSON);
      case X_WWW_FORM_URLENCODED:
        if (params != null && !params.isEmpty()) {
          List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
          for (Map.Entry<String, T> entry : params.entrySet()) {
            String value = (String) entry.getValue();
            if (value != null) {
              pairs.add(new BasicNameValuePair(entry.getKey(), value));
            }
          }
          return new UrlEncodedFormEntity(pairs, charset);
        } else {
          return null;
        }
      default:
        return new StringEntity(JsonUtils.toJson(params), ContentType.APPLICATION_JSON);
    }
  }

  private <T> CloseableHttpResponse executeHttpClient(HttpEntityEnclosingRequestBase httpClient, String url, Map<String, String> header,
      Map<String, T> params, Charset requestCharset, RequestBodyType requestBodyType) {
    // 如果url是空，则无法请求。
    if (StringUtils.isEmpty(url)) {
      throw new IllegalArgumentException("Unexpected url.");
    }
    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException ex) {
      ex.printStackTrace();
      throw new IllegalArgumentException("Unexpected url.");
    }
    httpClient.setURI(uri);
    // 生成Headers
    Header[] headers = buildHeaders(header);
    httpClient.setHeaders(headers);
    // 生成Params
    HttpEntity httpEntity = buildHttpEntity(params, requestCharset, requestBodyType);
    httpClient.setEntity(httpEntity);
    try {
      // 执行请求
      CloseableHttpResponse response = this.httpClient.execute(httpClient);
      return response;
    } catch (IOException ex) {
      throw new UnknownResponseException(ex);
    }
  }
}
