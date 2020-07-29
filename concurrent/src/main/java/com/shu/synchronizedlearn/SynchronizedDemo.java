package com.shu.synchronizedlearn;

/**
 * @description:  https://www.jianshu.com/p/d53bf830fa09
 * @author: shurunlong
 * @create: 2020-07-27 17:59
 */
public class SynchronizedDemo {
    public static void main(String[] args) {
        synchronized (SynchronizedDemo.class) {
        }
        method();
    }

    private static void method() {
    }
}

/*
* 1 对象锁机制
* 2 可重入性
*
*
* */
