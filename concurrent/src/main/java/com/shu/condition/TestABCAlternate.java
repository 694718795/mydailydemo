package com.shu.condition;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-23 11:20
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        final AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ad.loopA();
                }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    ad.loopB();
                }
            }
        }, "B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                for (int i = 0; i < 10; i++) {
                    ad.loopC();
                }
            }
        }, "C").start();
    }
}
