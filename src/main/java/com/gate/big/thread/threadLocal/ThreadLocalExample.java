package com.gate.big.thread.threadLocal;

import java.util.Random;

/**
 * 6,ThreadLocal
 * 该变量形式上共享，但却是by线程独立
 * Created by zhangchengfu on 2018/3/17.
 */
public class ThreadLocalExample {
    private static ThreadLocal<Integer> x = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(
                    new Runnable() {
                        @Override
                        public void run() {
                            int data = new Random().nextInt(80);
                            System.out.println(Thread.currentThread().getName()
                                    + " has put data :" + data);
                            x.set(data);
                            Person.getInstance().setName("name" + data);
                            Person.getInstance().setAge(data);
                            new A().print();
                            new B().print();
                        }
                    }
            ).start();
        }
    }

    static class A {
        public void print() {
            int data = x.get();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " get data " + data);
            Person myData = Person.getInstance();
            System.out.println("A from " + Thread.currentThread().getName()
                    + " getMyData: " + myData.getName() + ","
                    + myData.getAge());
        }
    }

    static class B {
        public void print() {
            int data = x.get();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " get data " + data);
            Person myData = Person.getInstance();
            System.out.println("B from " + Thread.currentThread().getName()
                    + " getMyData: " + myData.getName() + ","
                    + myData.getAge());
        }
    }
}

//javaBean的by线程的单例
class Person {
    private static ThreadLocal<Person> personThreadLocal = new ThreadLocal<Person>();
    private Person(){}
    public static /*无需synchronized*/ Person getInstance(){
        Person instance = personThreadLocal.get();
        if (instance == null) {
            instance = new Person();
            personThreadLocal.set(instance);
        }
        return instance;
    }

    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}