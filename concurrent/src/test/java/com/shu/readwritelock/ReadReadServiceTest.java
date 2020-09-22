package com.shu.readwritelock;

import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/28.
 * Mail to byhieg@gmail.com
 */
public class ReadReadServiceTest extends TestCase {
    public void testRead() throws Exception {
        ReadReadService service = new ReadReadService();
        Thread a = new Thread(service::read);
        a.setName("A");

        Thread b = new Thread(service::read);
        b.setName("B");

        a.start();
        b.start();

    }


    //获得读锁B 1573027977712
    //获得读锁A 1573027977712
    //几乎同时获取锁  同时执行  读读不互斥

}