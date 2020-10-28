package task;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MineHttpClient {
    public static void main(String[] args) {
        String url="http://localhost:8808/test";
        //1、创建httpClient
        CloseableHttpClient client = HttpClients.createDefault();
        //2、使用get方法
        HttpGet httpGet = new HttpGet(url);

        InputStream is = null;
        CloseableHttpResponse response = null;

        try{
            //3、执行请求、获取响应
            response = client.execute(httpGet);
            //4、获取到数据
            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                System.out.println(EntityUtils.toString(entity,"utf-8"));
                //关闭流
                EntityUtils.consume(entity);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
