package com.shu.send;

/**
 * @description: 数据发送服务
 * 1 注册
 * 2 数据直传
 * 3 订阅数据
 *
 * @author: shurunlong
 * @create: 2020-10-21 14:59
 */
public interface AbstractDefaultSender {


    //向上注册
    void requestRegister();



    //数据上报
    Object send(Object o);

}
