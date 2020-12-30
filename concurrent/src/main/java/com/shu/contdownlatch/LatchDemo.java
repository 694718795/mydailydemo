package com.shu.contdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @description:  10个线程同时去输出50000以内的偶数
 *
 * https://baijiahao.baidu.com/s?id=1663690872148615289&wfr=spider&for=pc
 * @author: shurunlong
 * @create: 2020-07-23 09:54
 */
public class LatchDemo implements Runnable{
/*    private CountDownLatch latch;
    public LatchDemo(){
    }
    @Override
    public void run() {
        for (int i = 0;i<5000;i++){
            if (i % 2 == 0){//50000以内的偶数
                System.out.println(i);
            }
        }
    }*/


    private CountDownLatch latch;
    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }
    @Override
    public void run() {
        synchronized (this) {
            try {
                for (int i = 0; i < 5000; i++) {
                    if (i % 2 == 0) {//50000以内的偶数
                        System.out.println(Thread.currentThread().getName()+"---------"+i);
                    }
                }
            } finally {
                latch.countDown();//每执行完一个就递减一个
            }
        }
    }
}
