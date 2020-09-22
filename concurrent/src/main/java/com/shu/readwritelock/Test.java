package com.shu.readwritelock;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @description: https://blog.csdn.net/u010512429/article/details/80011239
 * @author: shurunlong
 * @create: 2020-08-10 17:53
 */
public class Test {
    private Map<String, Object> map = new HashMap<>();
    private ReadWriteLock rwl = new ReentrantReadWriteLock();
    private Lock r = rwl.readLock();
    private Lock w = rwl.writeLock();



    private volatile boolean isUpdate;

    public void readWrite() {
        r.lock(); // 为了保证isUpdate能够拿到最新的值
        if (!isUpdate) {
            r.unlock();
            w.lock();
            map.put("xxx", "xxx");//1
            r.lock();//2
            w.unlock();//3
        }

        Object obj = map.get("xxx");//4
        System.out.println(obj);//5
        r.unlock();//6
    }

    public void readWrite1() {
        r.lock(); // 为了保证isUpdate能够拿到最新的值
        if (!isUpdate) {
            r.unlock();
            w.lock();
            map.put("xxx", "xxx");//1
//            r.lock();//2
            w.unlock();//3
        }

        Object obj = map.get("xxx");//4
        System.out.println(obj);//5
//        r.unlock();//6
    }

//一个线程要想同时持有写锁和读锁，必须先获取写锁再获取读锁；写锁可以“降级”为读锁；读锁不能“升级”为写锁。

    /*若在第2行和第6行上并不加读锁的获取和释放(即//2和//6被注释掉)，
    当前线程完成第1行的put操作后，进行第3行的写锁释放操作，
    若此时有另一个线程获得写锁并进行修改(即进行第1行操作)，
    那么当前获得读锁的线程无法感知，在进行第4行和第5行操作时就会发生线程安全性问题。
    若加上第2行和第6行，由于读锁和写锁之间是互斥的，当写锁释放的时候，由于第2行读锁的存在，
    新的线程并不能获得写锁，此时就保证了线程安全性。*/

}
