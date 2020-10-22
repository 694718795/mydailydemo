package com.shu.syslog;

import java.io.*;
import java.net.Socket;

/**
 * @description: https://blog.csdn.net/Ocean_999/article/details/84970705
 * @author: shurunlong
 * @create: 2020-09-22 15:54
 */
public class SokectThread implements Runnable {
    private BufferedReader reader;
    private Socket socket;
    //通过构造方法传递Socket
    public SokectThread(Socket clientSocket)
    {
        try
        {
            // 得到socket连接
            socket = clientSocket;
            // 得到客户端发来的消息
            InputStreamReader isReader = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(isReader);
            // 清空socket缓冲区数据
            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os);
            String ms = "********************";
            pw.write(ms);
            pw.flush();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        String messages;
        try
        {
            while ((messages = reader.readLine()) != null)
            {
                System.out.println("客户端请求的消息: " + messages);
            }
        } catch (IOException e){
            e.printStackTrace();
        }

    }

}
