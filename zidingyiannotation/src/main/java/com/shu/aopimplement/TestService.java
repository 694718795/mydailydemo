package com.shu.aopimplement;

import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-17 17:06
 */
@Service
public class TestService {

    @PrivateDataMethod
    @CachePut("asdfasdf")
    public Po save(Po po) {
        System.out.println(po.getName());
        System.out.println(po.getPassword());


        return po;
    }
}
