package com.gate.big.thread;

/**
 * 1,创建多线程
 * Created by zhangchengfu on 2018/3/16.
 */
public class MultiThread {
    public static void main(String[] args) {
        //通过继承Thread类
        Thread thread = new Thread(){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("1:" + Thread.currentThread().getName());
                    System.out.println("2:" + this.getName());
                }
            }
        };
        thread.start();

        //通过实现Runnable接口
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("3:" + Thread.currentThread().getName());
                }
            }
        });
        thread2.start();

        //如果继承runnable接口又实现了Thread类，会执行哪个？会执行：5:Thread-2
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        while (true) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("4:" + Thread.currentThread().getName());
                        }
                    }
                }
        ){
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("5:" + Thread.currentThread().getName());//5:Thread-2
                }
            }
        }.start();
    }
}
