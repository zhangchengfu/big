package com.gate.big.thread.threadLocal;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * ThreadLocal实现原理
 * Created by zhangchengfu on 2018/3/17.
 */
public class ThreadLocalSimulation {
    private static Map<Thread,Integer> threadData = new HashMap<Thread,Integer>();//核心

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int data = new Random().nextInt();
                    System.out.println(Thread.currentThread().getName()
                            + "  has put data :" + data);
                    threadData.put(Thread.currentThread(), data);
                    new A().get();
                    new B().get();
                }
            }).start();
        }
    }

    static class A {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data :" + data);
        }
    }

    static class B {
        public void get() {
            int data = threadData.get(Thread.currentThread());
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data :" + data);

        }
    }
}
