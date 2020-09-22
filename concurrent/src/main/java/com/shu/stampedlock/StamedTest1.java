package com.shu.stampedlock;

import javassist.bytecode.analysis.Executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @description: https://baijiahao.baidu.com/s?id=1649614767637994792&wfr=spider&for=pc
 * @author: shurunlong
 * @create: 2020-08-11 17:26
 */
public class StamedTest1 {

    private static StampedLock lock = new StampedLock();
    private static List<String> data=new ArrayList<String>();

    public static void write(){
        long stamped =-1;

        try {
            stamped=lock.writeLock();
            data.add("写线程写入的数据"+stamped);
            System.out.println("写入的数据是:"+stamped);
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }

    public static void read(){
        long stamped =-1;

        try {
            stamped=lock.readLock();
            for (String datum : data) {
                System.out.println("读的数据是:"+datum);
            }
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        Runnable readTask= () ->{
            for (;;){
//                read();
                optimisticRead();
            }
        };
        Runnable writeTask= () ->{
            for (;;){
                write();
            }
        };
        for (int i = 0; i < 9; i++) {
            executor.submit(readTask);
        }
        executor.submit(writeTask);
    }

    public  static  void optimisticRead(){
        //尝试去拿一个乐观锁
        long stamped=lock.tryOptimisticRead();
        //如果没有线程修改,我们再去获取一个读锁
        if (lock.validate(stamped)){

            try {
                stamped=lock.readLock();
                for (String datum : data) {
                    System.out.println("读的数据是:"+datum);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlockRead(stamped);
            }
        }
    }
}
