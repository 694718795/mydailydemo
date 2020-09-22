package cn.shu.queueDemo;

import java.util.List;

public class Consumer implements Runnable {

    private List list;

    public Consumer(List queue) {
        this.list = queue;
    }

    @Override
    public void run() {
        synchronized (list) {
            while (true) {
                while (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object object = list.remove(0);
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 消费");
                list.notifyAll();
            }
        }
    }
}
