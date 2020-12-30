package com.shu.test;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-08-04 16:55
 */
public abstract class Test_1_B /*extends Test_1_A*/{

    String Bage;

    public String getBage() {
        return Bage;
    }

    public void setBage(String bage) {
        Bage = bage;
    }

   /* public Test_1_B() {
        System.out.println("抽象类B");
        System.out.println("类B"+this.toString());
        System.out.println("类B"+super.toString());
    }*/

    public void dosomethingB(){
        System.out.println("抽象类B中的方法");
    }

    public void dosomethingB2(){
        System.out.println("抽象类B2中的方法");
    }


}
