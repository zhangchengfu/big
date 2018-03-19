package com.gate.big.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 9,Lock
 * ReetrantLock是具有synchronized功能的类
 * ReentrantReadWriteLock 粒度更细，读与读不互斥，写与写互斥，读与写互斥
 * Created by zhangchengfu on 2018/3/17.
 */
// 使用Lock改写synchronized例子
public class LockTest {
    public static void main(String[] args) {
        new LockTest().init();
    }
    private void init() {
        final Outputer outputer = new Outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("javaIsAPurelyObjectOrientedProgrammingLanguage");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outputer.output("c++IsAMulti-paradigmSystems-levelProgrammingLanguage");
                }
            }
        }).start();
    }

    static class Outputer {
        Lock lock = new ReentrantLock();

        public void output(String name) {
            int len = name.length();
            lock.lock();
            try {
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            } finally {
                lock.unlock();
            }
        }
    }
}
