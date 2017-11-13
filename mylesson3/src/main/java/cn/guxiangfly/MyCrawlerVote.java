package cn.guxiangfly;




import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * MyCrawlerVote
 *
 * @author guxiang
 * @date 2017/11/13
 */
public class MyCrawlerVote {
    public static void main(String[] args) throws IOException {
        String pattern = "\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{3}+\\s+\\d{4,5}";
        List list = FileUtils.readLines(new File("D:\\IntellijIDEAWorkSpace\\mySpider\\mylesson3\\src\\main\\resources\\iptables.txt"));
        for (Object o : list) {
            String matcher = getMatcher(pattern, o.toString());
            if (matcher!=null){
                String ip = matcher.split("\\s")[0];
                int port = Integer.parseInt(matcher.split("\\s")[1]);
                try {
                    executeRequest(ip, port);
                }catch (IOException e){
                    System.out.println("失败");
                    continue;
                }

            }
        }

    }

    private static void executeRequest(String ip, int port) throws IOException {
        Response execute = Request.Get("https://bbbtt2.com/intr/dedc2519750c0af9")
                .viaProxy(new HttpHost(ip,port)).connectTimeout(500)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Safari/537.36")
                .execute();
        String page = execute.returnContent().asString(Charset.forName("UTF-8"));
        System.out.println(page);
    }


    public static String getMatcher(String regex, String source) {
        String result = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(source);
        while (matcher.find()) {
            result = matcher.group(0);
        }
        return result;
    }


}
