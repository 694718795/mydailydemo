package com.shu.demo1;

import org.springframework.stereotype.Service;

import javax.jws.WebService;

/**
 * @description:  本demo参考: https://www.jianshu.com/p/5c3575cf6fa2
 *               webservice原理讲解:  https://blog.csdn.net/c99463904/article/details/76018436
 * @author: shurunlong
 * @create: 2020-10-19 16:16
 */
@Service
@WebService(serviceName = "WebServiceDemoService", // 与接口中指定的name一致
        targetNamespace = "http://demo1.shu.com", // 与接口中的命名空间一致,一般是接口的包名倒写
        endpointInterface = "com.shu.demo1.WebServiceDemoService" // 接口地址
)
public class WebServiceDemoServiceImpl implements WebServiceDemoService {

    @Override
    public String hello(String name) {
        return "hello"+name;
    }


}
