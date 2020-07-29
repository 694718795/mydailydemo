package com.shu.AtomicInteger;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:    https://www.jianshu.com/p/1f19835e05c0
 * @author: shurunlong
 * @create: 2020-07-23 11:40
 */
public class AtomicDemo implements Runnable{
//    private int i = 0;     //  结果出错   出现重复数据
//    public int getI(){
//        return i++;
//    }


    private AtomicInteger i = new AtomicInteger();    // 结果正常 无重复数据
    public int getI(){
        return i.getAndIncrement();
    }

//    private volatile int i = 0;     //结果出错出现重复数据
//    public int getI(){
//        return i++;
//    }
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getI());
    }



}
