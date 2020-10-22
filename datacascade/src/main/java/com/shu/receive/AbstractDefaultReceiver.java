package com.shu.receive;

/**
 * @description: 数据接收服务
 * @author: shurunlong
 * @create: 2020-10-21 15:00
 */
public interface AbstractDefaultReceiver {

    //响应注册
    Object responseRegister();

    //订阅配置
    void subscription(Object o);


    //数据上报
    Object receive(Object o);

}
