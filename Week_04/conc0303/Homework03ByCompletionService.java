package java0.conc0303;

import java.util.concurrent.*;

/**
 * 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池，
 * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？
 * 写出你的方法，越多越好，提交到github。
 *
 * 一个简单的代码参考：
 */
public class Homework03ByCompletionService {
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        
        long start=System.currentTimeMillis();
        // 在这里创建一个线程或线程池，
        // 异步执行 下面方法

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletionService completionService = new ExecutorCompletionService<>(executorService);

        Future<Integer> task = completionService.submit(() -> sum());
        int  result = task.get();
        
        // 确保  拿到result 并输出
        System.out.println("异步计算结果为："+result);
         
        System.out.println("使用时间："+ (System.currentTimeMillis()-start) + " ms");
        
        // 然后退出main线程
        executorService.shutdown();
    }
    
    private static int sum() {
        return fibo(36);
    }
    
    private static int fibo(int a) {
        if ( a < 2) 
            return 1;
        return fibo(a-1) + fibo(a-2);
    }

}
