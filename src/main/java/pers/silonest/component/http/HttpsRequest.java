package pers.silonest.component.http;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;

import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import pers.silonest.component.base.resource.ClassPathResource;

public class HttpsRequest extends AbstractHttpRequest {

  public HttpsRequest(String clientPath, String clientPwd, String jksPath, String jksPwd) {

    try {
      KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
      trustStore.load(new ClassPathResource(jksPath).getInputStream(), jksPwd.toCharArray());
      KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
      clientKeyStore.load(new ClassPathResource(clientPath).getInputStream(), clientPwd.toCharArray());
      SSLContext sslcontext =
          SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).loadKeyMaterial(clientKeyStore, clientPwd.toCharArray()) // 加载本地证书
              .build();
      // 去掉域名验证。
      SSLConnectionSocketFactory sslsf =
          new SSLConnectionSocketFactory(sslcontext, new String[] {"TLSv1", "TLSv1.1", "TLSv1.2", "SSLv3"}, null, new AllHostnameVerifier());
      super.httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
    } catch (Exception e) {
      throw new Error("Failed to initialize the server-side SSLContext", e);
    }

  }

  class AllHostnameVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String arg0, SSLSession arg1) {
      return true;
    }
  }
}
