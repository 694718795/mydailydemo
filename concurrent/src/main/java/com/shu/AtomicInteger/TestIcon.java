package com.shu.AtomicInteger;

/**
 * @description:  原子性
 * @author: shurunlong
 * @create: 2020-07-23 11:39
 */
public class TestIcon {
    public static void main(String[] args){
        AtomicDemo atomicDemo = new AtomicDemo();
        for (int x = 0;x < 20; x++){
            new Thread(atomicDemo).start();
        }
    }
}
