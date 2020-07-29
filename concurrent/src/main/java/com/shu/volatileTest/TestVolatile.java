package com.shu.volatileTest;

/**
 * @description:  https://www.jianshu.com/p/1f19835e05c0
 * @author: shurunlong
 * @create: 2020-07-22 16:29
 */
public class TestVolatile {

    public static void main(String[] args){ //这个线程是用来读取flag的值的
        ThreadDemo threadDemo = new ThreadDemo();
        Thread thread = new Thread(threadDemo);
        thread.start();
        while (true){
            System.out.println("if之外主线程读取到的flag = " + threadDemo.isFlag());
            if (threadDemo.isFlag()){
                System.out.println("主线程读取到的flag = " + threadDemo.isFlag());
                break;
            }
        }
    }
}
