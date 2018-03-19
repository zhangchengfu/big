package com.gate.big.thread;

import java.io.PrintStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 2,定时器Timer
 * 定时任务就是靠多线程实现的
 * Created by zhangchengfu on 2018/3/16.
 */
public class TimerTest {
    private static int count = 0;

    public static void main(String[] args) {
        class MyTimerTask extends TimerTask {
            @Override
            public void run() {
                count = (count+1) % 2;
                System.out.printf("bombing!");
                new Timer().schedule(new MyTimerTask(), 2000+2000*count);
            }
        }

        new Timer().schedule(new MyTimerTask(),2000);

        while (true) {
            System.out.println(new Date().getSeconds());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
