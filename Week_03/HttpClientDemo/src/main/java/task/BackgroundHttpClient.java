package task;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class BackgroundHttpClient {

    public String handle(String backServer){
        String url = backServer;
        //1、创建httpClient
        CloseableHttpClient client = HttpClients.createDefault();
        //2、使用get方法
        HttpGet httpGet = new HttpGet(url);

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
                return EntityUtils.toString(entity,"utf-8");
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

//    public static void main(String[] args) {
//        String url="http://localhost:8801";
//        //1、创建httpClient
//        CloseableHttpClient client = HttpClients.createDefault();
//        //2、使用get方法
//        HttpGet httpGet = new HttpGet(url);
//
//        CloseableHttpResponse response = null;
//
//        try{
//            //3、执行请求、获取响应
//            response = client.execute(httpGet);
//            //4、获取到数据
//            if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//                HttpEntity entity = response.getEntity();
//                System.out.println(EntityUtils.toString(entity,"utf-8"));
//                //关闭流
//                EntityUtils.consume(entity);
//            }
//        }catch (IOException e){
//            e.printStackTrace();
//        }finally {
//            if (response != null) {
//                try {
//                    response.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}
