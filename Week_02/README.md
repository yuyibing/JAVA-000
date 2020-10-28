##### week2_作业
###### 1.使用 GCLogAnalysis.java 自己演练一遍串行 / 并行 /CMS/G1 的案例。
运行环境：win10，内存12g，jdk8

应用不同的垃圾收集算法运行GCLogAnalysis.java，例：
```
java -XX:+UseSerialGC -Xms128m -Xmx128m -XX:+PrintGCDetails -XX:+PrintGCDateStamps GCLogAnalysis
```

运行过程中创建对象个数对比如下：

 -|128m| 256m | 512m | 1g | 2g | 4g
---|---|---|---|---|---|---
**SerialGC** | OOM | 4632 | 8321 | 8481 | 8392 | 5544 
**ParallelGC** | OOM | 3134 | 7353 | 10851 | 11776 | 7812
**ConcMarkSweepGC** | OOM | 4557 | 8736 | 11553 | 10172 | 9447
**G1GC** | OOM | OOM | 8359 | 10916 | 10377 | 11679

根据运行结果总结如下：
1. 堆内存设置较小如128M，会发生几次young gc后，之后因为old区占用比例大，频繁进行Full GC，但是每次Full GC效果不明显，堆内存逐渐耗尽，最终导致OOM；
2. SerialGC时，Young GC进行年轻代垃圾回收，Full GC只有堆中老年代进行垃圾回收；
3. ParallelGC时，Young GC进行年轻代垃圾回收，Full GC进行年轻代和老年代的垃圾回收；
4. 并行GC时，不配置Xms时，会比配置了Xmx=Xms时更早进行Young GC，因为初始化的年轻代会很小，很容易超出阈值进行垃圾回收；
5. CMS GC和并行GC，在堆内存都为4G时，CMS GC时产生的对象更多效果比并行GC要好，因为CMS GC在垃圾收集时只有特定的步骤才会进行暂停，线程暂停事件较短，可以处理更多的业务线程；
6. 串行GC在堆内存为4G时创建对象个数明显少于2G时的对象，原因是堆比较大时，业务暂停时间较长；


###### 2.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801 ，代码提交到 Github。
引入依赖：

```
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>4.5.13</version>
</dependency>
```
代码如下：

```
public class MineHttpClient {
    public static void main(String[] args) {
        String url="http://localhost:8801";
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
```
访问结果：

```
hello,nio
```

