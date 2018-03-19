package com.gate.big.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 15,阻塞队列
 * Created by zhangchengfu on 2018/3/19.
 */
/*阻塞队列实现了BlockingQueue接口,  是生产者消费者模型的典范, 通过锁实现

put和take方法才具有阻塞功能
阻塞队列与线程同步      :  两个大小为1的空/满阻塞队列可以实现condition或wait/notify的效果
阻塞队列与Semaphore :  阻塞队列是一个线程存入数据, 一个线程取出数据; Semaphore一般用作同一线程获取和释放*/
public class BlockingQueueTest {
    public static void main(String[] args) {
        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep((long) (Math.random() * 1000));
                            System.out.println(Thread.currentThread().getName() + "准备放数据");
                            queue.put(1);
                            System.out.println(Thread.currentThread().getName() + "已经放入数据：" + "队列目前有" + queue.size() + "个数据");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread().getName() + "准备取数据");
                        queue.take();
                        System.out.println(Thread.currentThread().getName()
                                + "已经取走数据, " + "队列目前有" + queue.size() + "个数据");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
