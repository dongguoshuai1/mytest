import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * @author dong
 * @Title: ESUtile
 * @ProjectName esTest
 * @Description: es工具类
 * @date 2019/1/2上午 10:06
 */
public class ESUtile {

    private static RestHighLevelClient client;

    private static RestClient restClient;

    public static RestHighLevelClient getClient(){
        if(client==null){
            client = new RestHighLevelClient(
                    RestClient.builder(
                            new HttpHost("localhost", 9200, "http")));

        }
        return client;
    }

    public static RestClient getLowClient(){
        if(restClient==null){
            RestClientBuilder builder = RestClient.builder(
                    new HttpHost("localhost", 9200, "http"));
            Header[] defaultHeaders = new Header[]{new BasicHeader("", "")};
//            builder.setDefaultHeaders(defaultHeaders);
            builder.setMaxRetryTimeoutMillis(10000);
            restClient = builder.build();
        }
        return restClient;
    }

}
