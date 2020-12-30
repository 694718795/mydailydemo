package com.shu.dcl;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-12-08 11:49
 */
public class DoubleLockTest {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println(DoubleLock.getInstance());
                }
            }).start();
        }


    }
}
