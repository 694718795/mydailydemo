package cn.shu.conponent;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static cn.shu.conponent.Constant.QUEUE_SIZE;
import static cn.shu.conponent.Constant.TIME_LIMIT;

@Component
public class ThreadClass {

    LinkedBlockingQueue<String> cacheQueue = new LinkedBlockingQueue<>(QUEUE_SIZE);
    private AtomicBoolean cCanRead = new AtomicBoolean(false);
    private AtomicInteger count = new AtomicInteger(0);

    private long waitTimeB = 0;
    long startTime = System.currentTimeMillis();

    private int x = 1;   //origin中的指针

    private void resetStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    private long waitTime() {
        return System.currentTimeMillis() - startTime;
    }

    /*
     * 缓存线程*/
    class CacheThread implements Runnable {
        @Override
        public void run() {
            int i = 0;
            while (i < Integer.MAX_VALUE) {
                String s = readFromX(x);
                if (s == null || s == "") {
                    System.out.println("已经取完所有字符");
                    return;
                }
                x = x + 1;
                try {
                    cacheQueue.put(s);
//                    System.out.println("队列长度:" + cacheQueue.size() + "--所取字符为:" + s);
                    count.addAndGet(1);
                    if (count.get() == QUEUE_SIZE) {
                        while (true) {
                            if (!cCanRead.get()) {
                                cCanRead.set(true);
                                count.set(0);
                                System.out.println("开始入库");
                                break;
                            } else {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception ex) {
                                    ex.getStackTrace();
                                }
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                i++;
                if (i == Integer.MAX_VALUE - 1) {
                    i = 0;
                }
            }
        }

        //从origin中读
        public String readFromX(int x) {
            int from = x;
            int to = x + 1;
            String path = "G:" + File.separator + "javademo" + File.separator + "srl" + File.separator + "queue" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "origin";

            String result = "";
            try {
                FileInputStream fis = new FileInputStream(path);
                BufferedInputStream bis = new BufferedInputStream(fis);
                bis.skip(from - 1);
                int c = 0;
                for (int i = 0; (i < to - from) && (c = bis.read()) != -1; i++) {
                    result += (char) c;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    /*
     * 写线程*/
    class WriteThread implements Runnable {
        @Override
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(new ThreadExceptionHandler());
            int j = 0;
            while (j < Integer.MAX_VALUE) {
                try {
                    if (cCanRead.get()) {
                        int queueLen = cacheQueue.size();
                        if (queueLen > 0) {
                            String[] strArray = new String[queueLen];
                            for (int i = 0; i < queueLen; i++) {
                                strArray[i] = cacheQueue.take();
                            }
                            String str = Arrays.stream(strArray).collect(Collectors.joining()).toString();
                            writeStr(str);
                            cCanRead.set(false);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

                System.out.println("写线程结束");
            }
        }

        //写到destination中
        public void writeStr(String str) {

            //定义文件路径，没有该文件会自动创建，如果路径有文件夹，一定要有，不会自动创建文件夹
//        String filename = "e:"+File.separator+"a"+File.separator+"b.txt";
            String path = "G:" + File.separator + "javademo" + File.separator + "srl" + File.separator + "queue" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator + "destination";
//        String path = Write_write.class.getClassLoader().getResource("origin").getPath();

            File file = new File(path);
            byte[] b = str.getBytes();    //将字符串转换成字节数
            OutputStream out = null;
            try {
                out = new FileOutputStream(file, true);    //实例化OutpurStream
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //写入
            try {
                out.write(b);        //写入
                out.close();        //关闭
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
    }


    /*
     * 时间检查线程 */
    class TimeCheckThread implements Runnable {
        @Override
        public void run() {

            int i = 0;
            while (i < Integer.MAX_VALUE) {
                waitTimeB = waitTime();
                // TODO 时间从配置中心获取
                if (waitTimeB >= TIME_LIMIT) {
                    System.out.println("TimeCheckThread运行");
                    System.out.println("waitting time out 5s...");
                    while (true) {
                        if (!cCanRead.get()) {
                            try {
                                System.out.println("waitting time out 5s to notify inpg thread");
                                cCanRead.set(true);
                                count.set(0);
                                break;
                            } catch (Exception ex) {
                                System.out.println("时间检查线程重置入库线程状态失败");
                            }
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {

                            Thread.currentThread().interrupt();
                        }
                    }
                }


                i++;
                if (i == Integer.MAX_VALUE - 1) {
                    i = 0;
                }
            }

        }

    }


    /**
     * 启动3个线程
     */
    //TODO 使用线程池+线程状态
    public void startThread() {
        CacheThread cacheThread = new CacheThread();
        Thread cacheThreadThread = new Thread(cacheThread, "CacheThread");
        cacheThreadThread.start();

        WriteThread writeThread = new WriteThread();
        Thread writeThreadThread = new Thread(writeThread, "WriteThread");
        writeThreadThread.start();

        TimeCheckThread timeCheckThread = new TimeCheckThread();
        Thread timeCheckThreadThread = new Thread(timeCheckThread, "timeCheckThread");
        timeCheckThreadThread.start();


    }

}