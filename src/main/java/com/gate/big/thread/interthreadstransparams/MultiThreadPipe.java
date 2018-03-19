package com.gate.big.thread.interthreadstransparams;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 管道
 * Created by zhangchengfu on 2018/3/17.
 */
public class MultiThreadPipe {
    public static void main(String[] args) {
        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream();
        try {
            pos.connect(pis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new Consumer(pis).start();
        new Producer(pos).start();
    }
}
class Producer extends Thread {
    private PipedOutputStream pos;
    public Producer(PipedOutputStream pos) {
        this.pos = pos;
    }

    @Override
    public void run() {
        int i = 8;
        try {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            pos.write(i);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Consumer extends Thread {
    private PipedInputStream pis;
    public Consumer(PipedInputStream pis) {
        this.pis = pis;
    }

    @Override
    public void run() {
        try {
            System.out.println(pis.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}