package pers.silonest.component.http;

import org.apache.http.impl.client.HttpClients;

public class HttpRequest extends AbstractHttpRequest {
  public HttpRequest() {
    this.httpClient = HttpClients.createDefault();
  }
}
