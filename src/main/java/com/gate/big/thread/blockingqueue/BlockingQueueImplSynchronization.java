package com.gate.big.thread.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 两个长度为1的空/满队列实现condition的效果
 * Created by zhangchengfu on 2018/3/19.
 */
public class BlockingQueueImplSynchronization {
    public static void main(String[] args) {

        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    business.sub1(i);
                }
            }
        }).start();

        for (int i = 1; i <= 20; i++) {
            business.sub2(i);
        }

    }

    static class Business {

        BlockingQueue<Integer> queue1 = new ArrayBlockingQueue<Integer>(1);
        BlockingQueue<Integer> queue2 = new ArrayBlockingQueue<Integer>(1);

        {
            try {
                System.out.println("init");
                queue2.put(1);    //queue1为空  queue为满
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sub1(int i) {
            try {
                queue1.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 1; j <= 10; j++) {
                System.out.println("sub thread sequece of " + j + ", loop of " + i);
            }
            try {
                queue2.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public void sub2(int i) {
            try {
                queue2.put(1);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            for (int j = 1; j <= 20; j++) {
                System.out.println("main thread sequece of " + j + ", loop of " + i);
            }
            try {
                queue1.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
