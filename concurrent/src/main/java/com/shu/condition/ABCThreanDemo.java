package com.shu.condition;

public class ABCThreanDemo {
    public static void main(String[] args) {
        ABCThreanDemo abcThreanDemo=new ABCThreanDemo();
        new Thread(abcThreanDemo.new MyRunable(C,A,A)).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(abcThreanDemo.new MyRunable(A,B,B)).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(abcThreanDemo.new MyRunable(B,C,C)).start();
    }
    public static final String A="A";
    public static final String B="B";
    public static final String C="C";
    public class MyRunable implements Runnable{
        String pre;
        String cou;
        String name;
        public MyRunable(String pre,String cou,String name){
            this.pre=pre;
            this.cou=cou;
            this.name=name;
        }
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                synchronized (pre) {
                    synchronized (cou) {
                        System.out.println(name);

                        cou.notifyAll();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        pre.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
