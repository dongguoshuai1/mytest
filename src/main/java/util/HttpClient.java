package util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

/**
 * @author dong
 * @Title: HttpClient
 * @ProjectName zxsd-controller-activity-manager
 * @Description: TODO
 * @date 2019/7/31上午 10:16
 */
public class HttpClient {

    public static void main(String[] args){
        try {
            SSLClient sslClient = new SSLClient();
            HttpUriRequest request = new HttpGet("https://api.open.21ds.cn/jd_api_v1/getjdunionitems?apkey=e685f1ce-7720-b936-298d-741429d6b366&keyword=%E6%89%8B%E6%9C%BA&pageIndex=1&pageSize=10");
            CloseableHttpResponse a = sslClient.execute(request);
            HttpEntity entity = a.getEntity();
            InputStream in = entity.getContent();
            int i = -1;
            while ((i=in.read())!=-1){
                System.out.print((char)i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static class SSLClient extends DefaultHttpClient {
        /**
         * 跳过验证
         * @return
         * @throws Exception
         */
        public SSLClient() throws Exception {
            SSLContext sc = SSLContext.getInstance("TLS");
            X509TrustManager trustManager = new X509TrustManager() {

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {

                }

                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {

                }
            };
            sc.init(null, new TrustManager[]{trustManager}, null);
            SSLSocketFactory ssf = new SSLSocketFactory(sc, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = this.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", 443, ssf));
        }
    }
}
