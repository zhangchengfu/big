package com.gate.big.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 7,线程池
 * 池化技术是防止频繁开关来提高系统性能，代价是必须损耗一定空间来保存池
 * Created by zhangchengfu on 2018/3/17.
 */
// 池化技术之线程池
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(3);// 限制线程数量
        //ExecutorService threadPool = Executors.newCachedThreadPool(); // 动态控制线程数量
        //ExecutorService threadPool = Executors.newSingleThreadExecutor(); // 跟一个线程类似，但可以保证线程挂了有新线程接替
        for(int i = 1; i <= 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 10; j++) {
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName() + " task:" + task + " loop:" + j);
                    }
                }
            });
        }
        System.out.println("all of tasks have committed!");
        threadPool.shutdown(); // 如果是shutdownNow方法会停止正在执行的任务

        // 带定时器的线程池 schedule方法：xx时间以后执行；scheduleAtFixedRate方法：xx时间后每隔yy时间执行
        Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("bombing!");
            }
        }, 6 ,2, TimeUnit.SECONDS);
    }


}
