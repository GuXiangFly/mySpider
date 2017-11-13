import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import org.testng.annotations.Test;


/**
 * HttpClientTest
 *
 * @author guxiang
 * @date 2017/5/27
 */
public class HttpClientTest {

    @Test
    public void testGet(){
        CloseableHttpClient client = HttpClients.createDefault();
        RequestBuilder requestBuilder = RequestBuilder.get("https://www.ip138.com").addParameter("k", "中国");

        /**
         * requestBuilder.build().toString()
         * 可以显示 具体的请求
         * 这是结果
         * GET /www.baidu.com?k=%E4%B8%AD%E5%9B%BD HTTP/1.1
         */
        //System.out.println(requestBuilder.build().toString());



    }

    @Test
    public void  testGet2() throws Exception{
        Response response = Request.Get("http://www.xicidaili.com/").execute();
        System.out.println(response.returnContent());
    }
}
