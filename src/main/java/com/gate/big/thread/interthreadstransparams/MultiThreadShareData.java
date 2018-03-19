package com.gate.big.thread.interthreadstransparams;

/**
 * 5,线程间传递参数
 * 共享变量
 * Created by zhangchengfu on 2018/3/16.
 */
//多个线程共享变量
//以类中变量为中介; 以传入的共同参数为中介; 匿名内部类以主线程main中变量为中介;
public class MultiThreadShareData {
    public static void main(String[] args) {
        //传入共享参数，每个线程执行相同的代码
        /*ShareData1 data1 = new ShareData1();
        new Thread(data1).start();
        new Thread(data1).start();*/

        //传入共享参数
        ShareData2 data2 = new ShareData2();
        new Thread(new MyRunnable1(data2)).start();
        new Thread(new MyRunnable2(data2)).start();

        // 匿名内部类实现变量的写法更简洁，不需要传参
        final ShareData2 data3 = new ShareData2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                data3.decrement();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                data3.increment();
            }
        }).start();
    }
}

// 方式1，如果每个线程执行相同的代码 -> 多个Thread共享同一个runnable中的对象   少有可能
class ShareData1 implements Runnable {
    private int count = 100;
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                count--;
                System.out.println("count: " + count + "  Thread:" + Thread.currentThread().getName());
            }
        }
    }
}

//方式2
class ShareData2 {
    private int j = 0;
    public synchronized void increment() {
        j++;
        System.out.println("j: " + j + "Thread: " + Thread.currentThread().getName());
    }
    public synchronized void decrement() {
        j--;
        System.out.println("j: " + j + "Thread: " + Thread.currentThread().getName());
    }
}
class MyRunnable1 implements Runnable {
    private ShareData2 data1;
    public MyRunnable1(ShareData2 data1) {
        this.data1 = data1;
    }
    @Override
    public void run() {
        data1.decrement();
    }
}
class MyRunnable2 implements Runnable {
    private ShareData2 data1;
    public MyRunnable2(ShareData2 data1) {
        this.data1 = data1;
    }

    @Override
    public void run() {
        data1.increment();
    }
}