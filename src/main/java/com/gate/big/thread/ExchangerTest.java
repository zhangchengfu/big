package com.gate.big.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 14,Exchanger
 * 两个线程间互相交换数据
 * Created by zhangchengfu on 2018/3/19.
 */
public class ExchangerTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        final Exchanger<String> exchanger = new Exchanger<>();
        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "王老吉";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + " 换出去");
                    Thread.sleep((long) (Math.random() * 2000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为 " + data2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });

        service.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String data1 = "加多宝";
                    System.out.println("线程" + Thread.currentThread().getName() + "正在把数据" + data1 + " 换出去");
                    Thread.sleep((long) (Math.random() * 2000));
                    String data2 = exchanger.exchange(data1);
                    System.out.println("线程" + Thread.currentThread().getName() + "换回的数据为 " + data2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
