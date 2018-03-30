package pers.silonest.component.http;

import java.nio.charset.Charset;
import java.util.Map;

public interface HttpClient {

  public SimplyHttpResponse<String> doGet(String url, Map<String, String> params);

  public SimplyHttpResponse<String> doGet(String url, Map<String, String> params, Charset charset);

  public SimplyHttpResponse<String> doGet(String url, Map<String, String> header, Map<String, String> params);

  public SimplyHttpResponse<String> doGet(String url, Map<String, String> header, Map<String, String> params, Charset charset);

  public SimplyHttpResponse<String> doPost(String url, Map<String, String> params);

  public SimplyHttpResponse<String> doPost(String url, Map<String, String> params, Charset charset);

  public SimplyHttpResponse<String> doPost(String url, Map<String, String> header, Map<String, String> params);

  public SimplyHttpResponse<String> doPost(String url, Map<String, String> header, Map<String, String> params, Charset charset);

  public SimplyHttpResponse<String> doPut(String url, Map<String, String> params);

  public SimplyHttpResponse<String> doPut(String url, Map<String, String> params, Charset charset);

  public SimplyHttpResponse<String> doPut(String url, Map<String, String> header, Map<String, String> params);

  public SimplyHttpResponse<String> doPut(String url, Map<String, String> header, Map<String, String> params, Charset charset);
  
  public SimplyHttpResponse<String> doDelete(String url,Map<String,String> params);
  
  public SimplyHttpResponse<String> doDelete(String url,Map<String,String> params,Charset charset);
  
  public SimplyHttpResponse<String> doDelete(String url,Map<String,String> header,Map<String,String> params);
  
  public SimplyHttpResponse<String> doDelete(String url,Map<String,String> header,Map<String,String> params,Charset charset);
}
