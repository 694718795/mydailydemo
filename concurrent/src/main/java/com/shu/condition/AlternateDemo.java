package com.shu.condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-23 11:20
 */
public class AlternateDemo {
    private int number = 1; //当前正在执行线程的标记

    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void loopA() {
        lock.lock();
        try {
            //1. 判断
            if (number != 1) {
                condition1.await();
            }
            //2. 打印
            System.out.print(Thread.currentThread().getName());
            //3. 唤醒
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB() {
        lock.lock();
        try {
            //1. 判断
            if (number != 2) {
                condition2.await();
            }
            //2. 打印
            System.out.print(Thread.currentThread().getName());
            //3. 唤醒
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC() {
        lock.lock();
        try {
            //1. 判断
            if (number != 3) {
                condition3.await();
            }
            //2. 打印
            System.out.print(Thread.currentThread().getName());
            //3. 唤醒
            number = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
