package pers.silonest.component.http;

import org.apache.http.impl.client.HttpClients;

public class DefaultHttpClient extends AbstractHttpClient {
  public DefaultHttpClient() {
    this.httpClient = HttpClients.createDefault();
  }
}
