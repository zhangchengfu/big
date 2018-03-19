package com.gate.big.thread.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10,Condition
 * Condition具有wait/notify功能的类，同样要配合Lock使用，但与synchronized的wait/notify不同
 * 这里同一个Lock下可以创建多个Condition对象，来实现粒度更细的控制
 * 一个condition
 * Created by zhangchengfu on 2018/3/18.
 */
// 使用Condition改写线程同步示例，Condition由Lock,newCondition()而来
// condition.await/signal 对应Mutex.wait/notify
public class ConditionTest {
    public static void main(String[] args) {
        final Business business = new Business();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 50; i++) {
                    business.sub(i);
                }
            }
        }).start();

        for (int i = 1; i <= 30; i++) {
            business.main(i);
        }
    }

    static class Business {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        private boolean bshouldSub = true;

        public void sub(int i) {
            lock.lock();
            try {
                while (!bshouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 10; j++) {
                    System.out.println("sub thread sequence of " + j + ",loop of " + i);
                }
                bshouldSub = false;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }

        public void main(int i) {
            lock.lock();
            try {
                while (bshouldSub) {
                    try {
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int j = 1; j <= 20; j++) {
                    System.out.println("main thread sequence of " + j + ",loop of " + i);
                }
                bshouldSub = true;
                condition.signal();
            } finally {
                lock.unlock();
            }
        }
    }
}
