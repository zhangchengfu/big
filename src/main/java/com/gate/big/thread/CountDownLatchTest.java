package com.gate.big.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 13,CountDownLatchTest
 * 线程通过等待计数器归零来实现同步，实现一个人/多个人等待一个人/多个人的完成
 * Created by zhangchengfu on 2018/3/19.
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final CountDownLatch cdOrder = new CountDownLatch(1); // 初始计数器的数为1
        final CountDownLatch cdAnswer = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("线程" + Thread.currentThread().getName() + "准备接受执行命令");
                        cdOrder.await();
                        System.out.println("线程" + Thread.currentThread().getName() + "已接到命令，开始执行");
                        //模拟handling
                        Thread.sleep((long) (Math.random() * 5000));
                        System.out.println("线程" + Thread.currentThread().getName() + "的分任务完成");
                        cdAnswer.countDown();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        try {
            Thread.sleep((long) (Math.random() * 5000));
            System.out.println("线程" + Thread.currentThread().getName() + "即将发送执行命令");
            cdOrder.countDown();
            System.out.println("线程" + Thread.currentThread().getName() + "已发送命令，任务正在处理");
            cdAnswer.await();
            System.out.println("线程" + Thread.currentThread().getName() + "主管的所有任务完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
