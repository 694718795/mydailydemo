package cn.shu.queueDemo;

import java.util.ArrayList;

/**
 * 关于synchronized 参考一下博客
 * https://blog.csdn.net/zjy15203167987/article/details/82531772
 * https://www.cnblogs.com/dream-to-pku/p/6308568.html
 */
public class Producer implements Runnable {

    private ArrayList list;

    private int capacity;

    public Producer(ArrayList queue,int capacity) {
        this.list = queue;
        this.capacity = capacity;
    }

    @Override
    public void run() {
        synchronized (list) {
            while (true) {
                while (list.size() == capacity) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object object = list.add(new Object());
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 生产");
                list.notifyAll();
            }
        }
    }
}

