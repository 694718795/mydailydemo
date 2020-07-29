package com.shu.test;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-27 17:21
 */
public  class Test1 {
    public static void main(String[] args) {
        synchronized (POabstractImpl.class) {
        }
        method();
    }

    private static void method() {
    }
}
