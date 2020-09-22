package com.shu.test;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-08-13 15:42
 */
public class Student {

    int id;
    int age;
    String username;
    String code;

    public Student() {
    }

    public Student(int id,int age, String username, String code) {
        this.id=id;
        this.age = age;
        this.username = username;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
