package com.shu.reflect;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-21 16:45
 */
public class User {

    @Mature(value="mature")
    public String userName;

    public String getUserName() {
        return userName;
    }

    @Mature(value="mature")//使用自定义注解，
    public void setUserName(String userName) {
        this.userName = userName;
    }

}
