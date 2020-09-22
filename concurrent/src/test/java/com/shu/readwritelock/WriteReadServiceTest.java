package com.shu.readwritelock;

import junit.framework.TestCase;

/**
 * Created by byhieg on 17/1/28.
 * Mail to byhieg@gmail.com
 */
public class WriteReadServiceTest extends TestCase {
    public void testWrite() throws Exception {

        WriteReadService service = new WriteReadService();
        Thread a = new Thread(service::write);
        a.setName("A");
        a.start();
        Thread.sleep(1000);

        Thread b = new Thread(service::read);
        b.setName("B");
        b.start();

        Thread.sleep(1000 * 30);
    }

    //获得写锁A 1573028228752
    //获得读锁B 1573028238752
    //同步执行  读写互斥

}