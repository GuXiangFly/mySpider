package cn.guxiangfly;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.params.ConnRouteParams;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.tools.ant.taskdefs.Exec;
import org.junit.Test;


import java.io.IOException;
import java.nio.charset.Charset;

import static org.apache.http.client.fluent.Request.*;

/**
 * CrawlerN1
 *
 * @author guxiang
 * @date 2017/11/13
 */
public class CrawlerN1 {

    @Test
    public void testGet() throws IOException{
        CloseableHttpClient client= HttpClients.createDefault();
        RequestBuilder requestBuilder = RequestBuilder.patch("http://api.xicidaili.com/free2016.txt");
        System.out.println(requestBuilder.build().toString());
        HttpGet httpGet = new HttpGet(requestBuilder.build().getURI());
       // httpGet.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("116.62.138.144",1080));


        //这一段与注释的效果等价
        String page =  client.execute(httpGet, new ResponseHandler<String>() {

            @Override
            public String handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
                String s = EntityUtils.toString(httpResponse.getEntity(), Charset.forName("UTF-8"));
                System.out.println(s);
                return s;
            }
        });

        System.out.println(page);


       /*
        CloseableHttpResponse execute = null;
        try {
            execute = client.execute(httpGet);
            String s = EntityUtils.toString(execute.getEntity(), Charset.forName("UTF-8"));
            System.out.println(s);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (execute!=null){
                execute.close();
            }
        }*/
    }


    /**
     * 推荐使用的方式
     * @throws IOException
     */
    @Test
    public void testGet2() throws IOException {
        Response execute = Request.Get("https://4dddnn.win/intr/b1afcb4f408b695c")
                .viaProxy(new HttpHost("113.68.11.12",9999))
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
                .execute();
        String page = execute.returnContent().asString(Charset.forName("UTF-8"));
        System.out.println(page);

    }
}
