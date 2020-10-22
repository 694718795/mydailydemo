package com.shu.syslog;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.sql.SQLException;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-09-22 16:36
 */
public class Broadcast {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        DatagramSocket ds=new DatagramSocket();
        InetAddress address=InetAddress.getByName("123.123.123.123");//输入本服务器IP
        byte [] bs="广播一条通知：".getBytes();
        DatagramPacket dp=new DatagramPacket(bs, bs.length,address,6666);//端口号自定义
        ds.send(dp);
        ds.close();
    }
}
