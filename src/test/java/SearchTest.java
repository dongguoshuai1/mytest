import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author dong
 * @Title: SearchTest
 * @ProjectName esTest
 * @Description: 搜索测试
 * @date 2019/1/2下午 3:24
 */
public class SearchTest {

    @Test
    public void test1(){
        SearchRequest searchRequest = new SearchRequest("coded_attr");
        searchRequest.types("coded_attr");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.termQuery("@version", 1));
        sourceBuilder.from(1000);
        sourceBuilder.size(9);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = ESUtile.getClient().search(searchRequest);
            RestStatus status = searchResponse.status();
            TimeValue took = searchResponse.getTook();
            Boolean terminatedEarly = searchResponse.isTerminatedEarly();
            boolean timedOut = searchResponse.isTimedOut();
            SearchHits hits = searchResponse.getHits();
            SearchHit[] searchHits = hits.getHits();
            for (SearchHit hit : searchHits) {
                System.out.println(hit);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                ESUtile.getClient().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void lowTest(){
        try {
            // 使用同步方法发送请求
            Map<String, String> params = Collections.emptyMap();
            String queryString = "{" +
                    "  \"size\": 20," +
                    "  \"query\": {" +
                    "   \"must\": {" +
                    "     \"id\": {" +
                    "       \"term\": \"371982\"" +
                    "     }" +
                    "   }" +
                    "  }" +
                    "}";
            HttpEntity entity = new NStringEntity(queryString, ContentType.APPLICATION_JSON);
            Response response = ESUtile.getLowClient().performRequest("GET","/coded_attr/coded_attr/_search",params,entity);
            HttpEntity resultEntity = response.getEntity();
            String body = EntityUtils.toString(resultEntity);
            System.out.println(resultEntity);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                ESUtile.getLowClient().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Test
    public void testAsync(){
        try {
            ResponseListener responseListener = new ResponseListener() {
                public void onSuccess(Response response) {
                    // 定义请求成功执行时需要做的事情
                    System.out.println("成功");
                }
                public void onFailure(Exception exception) {
                    // 定义请求失败时需要做的事情，即每当发生连接错误或返回错误状态码时做的操作。
                }
            };
            Map<String, String>  params = Collections.emptyMap();
            ESUtile.getLowClient().performRequestAsync("GET","/coded_attr/coded_attr/371982",responseListener);
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            try {
                ESUtile.getLowClient().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
