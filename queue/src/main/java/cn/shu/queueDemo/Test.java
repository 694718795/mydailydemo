package cn.shu.queueDemo;

import java.util.ArrayList;

/**
 * 参考博客:  https://blog.csdn.net/lppl010_/article/details/80810440
 */
public class Test {

    public static void main(String[] args) {

        ArrayList list = new ArrayList(3);
        for (int i = 0; i < 3; i++) {
            new Thread(new Producer(list, 3), "生产者" + i).start();
            new Thread(new Consumer(list), "消费者" + i).start();
        }
    }
}

