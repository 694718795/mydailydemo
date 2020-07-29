package com.shu.semaphore;

/**
 * @description:  https://www.cnblogs.com/klbc/p/9500947.html
 * @author: shurunlong
 * @create: 2020-07-21 10:25
 */
public class MyThread extends Thread {
    private SemaphoreService service;

    public MyThread(String name, SemaphoreService service) {
        super();
        this.setName(name);
        this.service = service;
    }

    @Override
    public void run() {
        this.service.doSomething();
    }
}
