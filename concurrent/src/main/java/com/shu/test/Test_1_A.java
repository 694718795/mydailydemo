package com.shu.test;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-08-04 16:54
 */
public class Test_1_A {

    private String Aname;


    public String getAname() {
        return Aname;
    }

    public Test_1_A() {
        System.out.println("类A");
        System.out.println("类A"+this.toString());
        System.out.println("类A"+super.toString());

    }

    public void setAname(String aname) {
        Aname = aname;
    }

    public void dosomethingA(){
        System.out.println("A类中的方法");
    }
}
