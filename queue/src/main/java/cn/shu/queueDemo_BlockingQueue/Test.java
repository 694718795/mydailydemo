package cn.shu.queueDemo_BlockingQueue;

import java.util.concurrent.BlockingQueue;

/**
 * 参考博客: https://blog.csdn.net/lppl010_/article/details/80810440
 */
public class Test {

    public static void main(String[] args) {
        BlockingQueue queue = new MyBlockingQueue(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(queue), "生产者" + i).start();
            new Thread(new Consumer(queue), "消费者" + i).start();
        }
    }
}

