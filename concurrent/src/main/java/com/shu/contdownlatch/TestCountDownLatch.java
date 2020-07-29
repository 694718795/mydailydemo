package com.shu.contdownlatch;


import java.util.concurrent.CountDownLatch;

/**
 * @description: 闭锁  https://www.jianshu.com/p/1f19835e05c0
 * @author: shurunlong
 * @create: 2020-07-23 09:53
 */

public class TestCountDownLatch {
    public static void main(String[] args){
/*        LatchDemo ld = new LatchDemo();
        long start = System.currentTimeMillis();
        for (int i = 0;i<10;i++){
            new Thread(ld).start();
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为："+(end - start)+"秒");*/

        final CountDownLatch latch = new CountDownLatch(10);//有多少个线程这个参数就是几
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(ld).start();
        }
        try {
            latch.await();//这10个线程执行完之前先等待
        } catch (InterruptedException e) {
        }
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为：" + (end - start));
    }
}
