package com.shu.readwritelock;

import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/28.
 * Mail to byhieg@gmail.com
 */
public class WriteWriteServiceTest extends TestCase {
    public void testWrite() throws Exception {
        WriteWriteService service = new WriteWriteService();
        Thread a = new Thread(service::write);
        a.setName("A");
        Thread b = new Thread(service::write);
        b.setName("B");

        a.start();
        b.start();
        Thread.sleep(1000 * 30);
    }


    //获得写锁A 1573028174675
    //获得写锁B 1573028184675
    //同步执行   写写互斥

}