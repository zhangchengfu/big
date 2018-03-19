package com.gate.big.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 11,Semaphore
 * Created by zhangchengfu on 2018/3/18.
 */
//

//Semaphore信号量, 互斥锁保证多个线程同时访问同一个资源时的线程安全性, 信号量让线程动态匹配现有资源数, 来保证同时访问多个资源时的线程安全性, 并发更高.
//Lock是哪个线程拿哪个线程负责释放; 信号量可以是一个线程获取, 另一个线程释放, 这个特性能用于死锁恢复.
public class SemaphoreTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i< 10; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(); /* 线程进入是获取信号量 */
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() +
                            "进入,当前已有" + (3-semaphore.availablePermits()) + "个并发");
                    try {
                        Thread.sleep((long) (Math.random()*1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "即将离开");
                    semaphore.release(); // 线程结束时释放信号量
                }
            };
            service.execute(runnable);
        }
        service.shutdown();
    }
}
