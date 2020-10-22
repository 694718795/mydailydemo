package com.shu.demo1;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @description:  本demo参考: https://www.jianshu.com/p/5c3575cf6fa2
 * webservice原理讲解:  https://blog.csdn.net/c99463904/article/details/76018436
 * @author: shurunlong
 * @create: 2020-10-19 16:15
 */
@WebService
public interface WebServiceDemoService {

    @WebMethod
    String hello(@WebParam(name = "name")String name);

}
