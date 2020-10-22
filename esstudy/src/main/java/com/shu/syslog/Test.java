package com.shu.syslog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shu.springbootes.po.BookBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.*;


/**
 * @description:
 * @author: shurunlong
 * @create: 2020-09-22 16:04
 */
@Component
public class Test implements ApplicationRunner {


    @Override
    public void run(ApplicationArguments args) throws Exception {
        InetAddress address = InetAddress.getByName("127.0.0.1");//输入本服务器IP
        DatagramSocket datagramSocket = null;

        datagramSocket = new DatagramSocket(514, address);

        while (true) {
            byte[] bs = new byte[65536];
            DatagramPacket dp = new DatagramPacket(bs, bs.length);
            datagramSocket.receive(dp);
            String s = new String(dp.getData());
            System.out.println("收到的数据为：" + s + ";收到的位移偏量为：" + dp.getOffset() + ";有效长度为：" + dp.getLength());


        }
    }
}
