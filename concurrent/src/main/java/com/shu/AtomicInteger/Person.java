package com.shu.AtomicInteger;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-23 11:31
 */
public class Person {

    volatile long id;
    public Person(long id) {
        this.id = id;
    }
    public String toString() {
        return "id:"+id;
    }
}
