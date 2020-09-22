package cn.shu.conponent;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class ThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("An exception has been captured");
        log.error("Thread name is: {}", t.getName());
        log.error("Exception: {}, {}", e.getClass(), e.getMessage());
        log.error("Stack Trace: {}", e.getStackTrace());
        log.error("Thread status is : {}", t.getState());
        ThreadClass threadClass = new ThreadClass();
        ThreadClass.WriteThread alarmInPgThread = threadClass.new WriteThread();
        new Thread(alarmInPgThread, "write").start();
    }
}
