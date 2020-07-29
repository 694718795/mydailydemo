package com.shu.volatileTest;

import lombok.Data;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-22 16:30
 */
@Data
public class ThreadDemo implements Runnable{//这个线程是用来修改flag的值的
    public  boolean flag = false;
    @Override
    public void run() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        flag = true;
        System.out.println("ThreadDemo线程修改后的flag = " + isFlag());
    }
}
