package com.gate.big.job;

public class Thread2 implements Runnable {
    @Override
    public void run() {
        System.out.println("run.");
        throw new RuntimeException("Problem");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new Thread2());
        thread.start();
        System.out.println("End of method.");
    }
}
