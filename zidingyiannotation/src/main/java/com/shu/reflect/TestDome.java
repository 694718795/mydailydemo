package com.shu.reflect;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-21 16:46
 */
public  class TestDome {
    @SuppressWarnings("rawtypes")
    public static Object createUser() throws Exception {
        //通过反射获取User对象
        String pack="com.shu.reflect.User";
        Class classz=Class.forName(pack);

        @SuppressWarnings("unchecked")
        Object user =classz.getConstructor().newInstance();
        Method[]arr=classz.getMethods();
        for(Method method:arr) {
            if(method.isAnnotationPresent(Mature.class)) {//判断方法是否存在注解
                Mature mature=method.getAnnotation(Mature.class);//获取该自定义注解
                method.invoke(user, mature.value());//将值赋值到user对象
            }

        }

        return user;    //最终返回被赋值的user对象
    }


    public static void main(String[] args) {
        try {
            User user=(User) createUser();
            System.out.println("username:"+user.getUserName());  //username:mature
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
