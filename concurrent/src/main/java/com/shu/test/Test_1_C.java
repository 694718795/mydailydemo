package com.shu.test;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-11-24 17:10
 */
public class Test_1_C extends Test_1_B {



    String hahahaC;

    public String getHahahaC() {
        return hahahaC;
    }

    public void setHahahaC(String hahahaC) {
        this.hahahaC = hahahaC;
    }

    public Test_1_C() {
        super();
    }

    @Override
    public void dosomethingB2(){
        System.out.println("抽象类B2中的方法在C中的重写");
    }
}
