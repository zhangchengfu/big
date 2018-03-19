package com.gate.big.thread;

import java.util.concurrent.*;

/**
 * 8,Callable接口与Future
 * 能实现返回线程执行结果的效果
 * Created by zhangchengfu on 2018/3/17.
 */
// 返回结果的任务
public class CallableAndFuture {
    public static void main(String[] args) {
        // 其一
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                // 模拟handling
                Thread.sleep(2000);
                return "hello";
            }
        });
        System.out.println("等待结果");

        try {
            System.out.println("拿到结果：" + future.get()); // 阻塞等待结果，还有个get方法的
            //重载版本，带超时参数，超时抛异常，future/get特点，任务合理分解，在需要任务结束时调用get
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        threadPool.shutdown(); // 不用该函数，主线程是不会退出的

        // 其二
        // ExecutorCompletionService包装线程池，take方法返回最先完成的Future任务
        ExecutorService threadPool2 = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(threadPool2);
        for (int i = 1; i <= 10; i++) {
            final int seq = i;
            completionService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    // 模拟handling
                    Thread.sleep(2000);
                    return seq;
                }
            });
        }
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println(completionService.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        threadPool2.shutdown();
    }
}
