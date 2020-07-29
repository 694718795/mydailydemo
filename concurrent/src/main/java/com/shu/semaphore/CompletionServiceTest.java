package com.shu.semaphore;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-10 10:22
 */
public class CompletionServiceTest {
    private static final Semaphore avialable = new Semaphore(5,true);

    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(10);     //需要2s，如果将8改成10，则只需要1s
        CompletionService<Boolean> cs = new ExecutorCompletionService<>(pool);
        int sum =100;
        int seg = 5;
        for(int i=0;i<sum/seg;i++){
            int begin = i*seg;
            int end = (i+1)*seg-1;
            Callable<Boolean> task = createTask(begin, end);
            cs.submit(task);
        }
        pool.shutdown();
    }

    public static String getNow(){
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        return sdf.format(new Date());

    }

    public static Callable<Boolean> createTask(int begin, int end){
        Callable<Boolean> task = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                try {
                    avialable.acquire();
                    System.out.println(getNow());
                    Thread.sleep(3000);
                    System.out.println(getNow());
                    System.out.println(Thread.currentThread().getName()+"--插入5条数据完成,begin:"+begin+",end:"+end);
                    avialable.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
                return true;
            }
        };
        return task;
    }
}
