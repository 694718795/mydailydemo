package cn.shu.queueDemo_BlockingQueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                queue.put(new Object());
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 生产");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

