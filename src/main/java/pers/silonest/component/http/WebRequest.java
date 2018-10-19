package pers.silonest.component.http;

import java.nio.charset.Charset;
import java.util.Map;

public interface WebRequest {

  public <T> WebResponse<String> doGet(String url, Map<String, T> params);

  public <T> WebResponse<String> doGet(String url, Map<String, T> params, Charset charset);

  public <T> WebResponse<String> doGet(String url, Map<String, String> header, Map<String, T> params);

  public <T> WebResponse<String> doGet(String url, Map<String, String> header, Map<String, T> params, Charset charset);


  public <T> WebResponse<String> doPost(String url, Map<String, T> params);

  public <T> WebResponse<String> doPost(String url, Map<String, String> header, Map<String, T> params);

  public <T> WebResponse<String> doPost(String url, Map<String, String> header, Map<String, T> params, RequestBodyType bodyType);

  public <T> WebResponse<String> doPost(String url, Map<String, String> header, Map<String, T> params, Charset requestCharset,
      Charset responseCharset, RequestBodyType requestBodyType);


  public <T> WebResponse<String> doPut(String url, Map<String, T> params);

  public <T> WebResponse<String> doPut(String url, Map<String, String> header, Map<String, T> params);

  public <T> WebResponse<String> doPut(String url, Map<String, String> header, Map<String, T> params, RequestBodyType bodyType);

  public <T> WebResponse<String> doPut(String url, Map<String, String> header, Map<String, T> params, Charset requestCharset, Charset responseCharset,
      RequestBodyType requestBodyType);


  public <T> WebResponse<String> doDelete(String url, Map<String, T> params);

  public <T> WebResponse<String> doDelete(String url, Map<String, T> params, Charset charset);

  public <T> WebResponse<String> doDelete(String url, Map<String, String> header, Map<String, T> params);

  public <T> WebResponse<String> doDelete(String url, Map<String, String> header, Map<String, T> params, Charset charset);
}
