package com.shu.condition;

import com.shu.contdownlatch.LatchDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @description: 编写一个程序，开启 3 个线程，这三个线程的 ID 分别为 A、B、C，
 * 每个线程将自己的 ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 * 如：ABCABCABC…… 依次递归
 * @author: shurunlong
 * @create: 2020-07-22 17:24
 */
@RestController
@RequestMapping
public class Demo1 {
    static int times = 10;
    AtomicReference result = new AtomicReference("");


    @GetMapping("/test")
    public void method() {
        final CountDownLatch latch = new CountDownLatch(3);//有多少个线程这个参数就是几


        ThreadA threadA = new ThreadA(latch);
        Thread a = new Thread(threadA);
        a.start();

        ThreadB threadB = new ThreadB(latch);
        Thread b = new Thread(threadB);
        b.start();

        ThreadC threadC = new ThreadC(latch);
        Thread c = new Thread(threadC);
        c.start();




        try {
            latch.await();//这10个线程执行完之前先等待
        } catch (InterruptedException e) {
        }

        System.out.println(result.get());


    }

    class ThreadA implements Runnable {
        private CountDownLatch latch;

        public ThreadA(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            synchronized (this) {
                try {
                    while (true) {
                        String string = (String) result.get();
                        String substring = getlastString(string);
                        if (substring.equals("") || substring.equals("C")) {
                            string = string + "A";
                        }
                        if (string.length() < 31) {
                            result.set(string);
                        } else {
                            break;
                        }
                    }
                } finally {
                    latch.countDown();//每执行完一个就递减一个
                }
            }

        }
    }

    class ThreadB implements Runnable {
        private CountDownLatch latch;

        public ThreadB(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            synchronized (this) {
                try {
                    while (true) {
                        String string = (String) result.get();
                        String substring = getlastString(string);
                        if (substring.equals("A")) {
                            string = string + "B";
                        }
                        if (string.length() < 31) {
                            result.set(string);
                        }else {
                            break;
                        }

                    }
                } finally {
                    latch.countDown();//每执行完一个就递减一个
                }
            }

        }
    }


    class ThreadC implements Runnable {
        private CountDownLatch latch;

        public ThreadC(CountDownLatch latch) {
            this.latch = latch;
        }
        @Override
        public void run() {
            synchronized (this) {
                try {
                    while (true) {
                        String string = (String) result.get();
                        String substring = getlastString(string);
                        if (substring.equals("B")) {
                            string = string + "C";
                        }
                        if (string.length() < 31) {
                            result.set(string);
                        }else {
                            break;
                        }

                    }
                } finally {
                    latch.countDown();//每执行完一个就递减一个
                }
            }

        }
    }

    public String getlastString(String string) {
        if (string.length() == 0) {
            return "";
        }

        //                String substring = string.substring(string.length() - 1);
        int length = string.toCharArray().length;
        char c = string.toCharArray()[length - 1];
        String substring = c + "";

        return substring;
    }
}





