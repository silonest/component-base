package pers.silonest.component.http;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContexts;

import java.security.KeyStore;

import javax.net.ssl.SSLContext;

import pers.silonest.component.base.resource.ClassPathResource;

public class SslHttpClient extends AbstractHttpClient {

  private final String KEY_STORE_TYPE_P12 = "PKCS12";
  private final String KEY_STORE_TYPE_JKS = "JKS";

  public static HttpClientConnectionManager CONNECTION_MANAGER = null;

  public SslHttpClient(String clientPath, String clientPwd, String jksPath, String jksPwd) {
    try {
      // // 1、导入自己证书
      // KeyStore selfCert = KeyStore.getInstance(KEY_STORE_TYPE_P12);
      // selfCert.load(new ClassPathResource(clientPath).getInputStream(), clientPwd.toCharArray());
      // KeyManagerFactory kmf = KeyManagerFactory.getInstance("sunx509");
      // kmf.init(selfCert, clientPwd.toCharArray());
      //
      // // 2、导入服务器CA证书
      // KeyStore caCert = KeyStore.getInstance(KEY_STORE_TYPE_JKS);
      // caCert.load(new ClassPathResource(jksPath).getInputStream(), jksPwd.toCharArray());
      // TrustManagerFactory tmf = TrustManagerFactory.getInstance("sunx509");
      // tmf.init(caCert);
      //
      // SSLContext sc = SSLContext.getInstance("TLS");
      // sc.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
      // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sc);
      // this.httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

      System.out.println("init conection pool...");

      // InputStream ksis = new FileInputStream(new File(keyStoreFile));
      // InputStream tsis = new FileInputStream(new File(trustStoreFile));

      KeyStore ks = KeyStore.getInstance("PKCS12");
      ks.load(new ClassPathResource(clientPath).getInputStream(), clientPwd.toCharArray());

      KeyStore ts = KeyStore.getInstance("JKS");
      ts.load(new ClassPathResource(jksPath).getInputStream(), jksPwd.toCharArray());

      SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(ks, clientPwd.toCharArray())
          // 如果有 服务器证书
          .loadTrustMaterial(ts, new TrustSelfSignedStrategy())
          // 如果没有服务器证书，可以采用自定义 信任机制
          // .loadTrustMaterial(null, new TrustStrategy() {
          //
          // // 信任所有
          // public boolean isTrusted(X509Certificate[] arg0,
          // String arg1) throws CertificateException {
          // return true;
          // }
          //
          // })
          .build();
      SSLConnectionSocketFactory sslsf =
          new SSLConnectionSocketFactory(sslContext, new String[] {"TLSv1"}, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

      Registry<ConnectionSocketFactory> registry =
          RegistryBuilder.<ConnectionSocketFactory>create().register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslsf).build();
      CONNECTION_MANAGER = new PoolingHttpClientConnectionManager(registry);
      this.httpClient = HttpClients.custom().setConnectionManager(CONNECTION_MANAGER).build();
    } catch (Exception ex) {
      throw new IllegalArgumentException(ex);
    }
  }
}
