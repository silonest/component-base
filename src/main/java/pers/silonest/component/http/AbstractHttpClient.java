package pers.silonest.component.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.net.ConnectException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pers.silonest.component.util.StringUtils;

public abstract class AbstractHttpClient implements HttpClient {

  final static Charset UTF_8 = Charset.forName("UTF-8");

  CloseableHttpClient httpClient;

  @Override
  public SimplyHttpResponse<String> doGet(String url, Map<String, String> params) {
    return doGet(url, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doGet(String url, Map<String, String> params, Charset charset) {
    return doGet(url, null, params, charset);
  }

  @Override
  public SimplyHttpResponse<String> doGet(String url, Map<String, String> header, Map<String, String> params) {
    return doGet(url, header, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doGet(String url, Map<String, String> header, Map<String, String> params, Charset charset) {
    if (StringUtils.isEmpty(url)) {
      throw new IllegalArgumentException("Unexpected url.");
    }
    try {
      if (params != null && !params.isEmpty()) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
          String value = entry.getValue();
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
      SimplyHttpResponse<String> result = new SimplyHttpResponse<String>(httpStatusCode, content);
      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new IllegalArgumentException(ex);
    }
  }

  @Override
  public SimplyHttpResponse<String> doPost(String url, Map<String, String> params) {
    return doPost(url, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doPost(String url, Map<String, String> params, Charset charset) {
    return doPost(url, null, params, charset);
  }

  @Override
  public SimplyHttpResponse<String> doPost(String url, Map<String, String> header, Map<String, String> params) {
    return doPost(url, header, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doPost(String url, Map<String, String> header, Map<String, String> params, Charset charset) {
    if (StringUtils.isEmpty(url)) {
      throw new IllegalArgumentException("Unexpected url.");
    }
    try {
      List<NameValuePair> pairs = null;
      if (params != null && !params.isEmpty()) {
        pairs = new ArrayList<NameValuePair>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
          String value = entry.getValue();
          if (value != null) {
            pairs.add(new BasicNameValuePair(entry.getKey(), value));
          }
        }
      }
      URI uri = new URI(url);
      HttpPost httpPost = new HttpPost(uri);
      if (pairs != null && pairs.size() > 0) {
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, UTF_8));
      }
      httpPost.setHeader("Accept-Encoding", "gzip, deflate");
      if (header != null && !header.isEmpty()) {
        for (Entry<String, String> entry : header.entrySet()) {
          String name = entry.getKey().trim();
          String value = entry.getValue().trim();
          if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
            httpPost.setHeader(name, value);
          }
        }
      }
      CloseableHttpResponse response = httpClient.execute(httpPost);
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
      SimplyHttpResponse<String> result = new SimplyHttpResponse<String>(httpStatusCode, content);
      return result;
    } catch (Exception ex) {
      ex.printStackTrace();
      throw new IllegalArgumentException(ex);
    }
    // return null;
  }

  @Override
  public SimplyHttpResponse<String> doPut(String url, Map<String, String> params) {
    return doPut(url, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doPut(String url, Map<String, String> params, Charset charset) {
    return doPut(url, null, params, charset);
  }

  @Override
  public SimplyHttpResponse<String> doPut(String url, Map<String, String> header, Map<String, String> params) {
    return doPut(url, header, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doPut(String url, Map<String, String> header, Map<String, String> params, Charset charset) {
    return null;
  }

  @Override
  public SimplyHttpResponse<String> doDelete(String url, Map<String, String> params) {
    return doDelete(url, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doDelete(String url, Map<String, String> params, Charset charset) {
    return doDelete(url, null, params, charset);
  }

  @Override
  public SimplyHttpResponse<String> doDelete(String url, Map<String, String> header, Map<String, String> params) {
    return doDelete(url, header, params, UTF_8);
  }

  @Override
  public SimplyHttpResponse<String> doDelete(String url, Map<String, String> header, Map<String, String> params, Charset charset) {
    return null;
  }
}
