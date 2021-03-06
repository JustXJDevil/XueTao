package com.futuer.sm.xt;

import com.future.sm.xt.WebApplication;
import com.future.sm.xt.util.HttpClientService;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = {WebApplication.class})
@RunWith(SpringRunner.class)
public class TestHttpClient {
    /**
     * 1.实例化httpClient对象
     * 2.准备url请求地址  https://www.baidu.com/
     * 3.封装请求方式对象   GET/POST/PUT/DELETE
     * 4.发起http请求.获取服务器响应.
     * 5.判断返回值状态码信息 200.
     * 6.从响应对象中获取服务器返回值数据.
     */
    @Test
    public void testGET() throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        String url = "https://www.baidu.com/";
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = client.execute(get);
        if (response.getStatusLine().getStatusCode()==200){
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity,"UTF-8");
            System.out.println(result);
        }
    }

    @Autowired
    private HttpClientService httpClientService;
    @Test
    public void test02(){
        //http://manage.xt.com/web/item/findItemById?itemId=562379
        String url = "http://manage.xt.com/web/item/findItemById";
        Map<String,String> params = new HashMap<>();
        params.put("itemId","562379");
        String result = httpClientService.doGet(url,params);
        System.out.println(result);

    }
}
