package com.shu.aopimplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-05-19 16:10
 */
@RequestMapping
@RestController
public class TestConroller {

    @GetMapping("/test")
    public String test() {
        return "ok";
    }

    @Autowired
    private TestService testService;

    @Autowired
    private AESEncrypt aesEncrypt;



    @PostMapping ("/asynctest")
    public @ResponseBody Po getvalue(@RequestBody Po po) {
        Po save = testService.save(po);
        return save;
    }

    @GetMapping ("/testjimi")
    public String asynctest(String name) {
        String encrypt = AESEncrypt.encrypt(name,"1111111111111111");
        String desEncrypt = AESEncrypt.desEncrypt(encrypt,"1111111111111111");
        System.out.println(desEncrypt);

        return encrypt;
    }
}
