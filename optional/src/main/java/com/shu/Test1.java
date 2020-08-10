package com.shu;


import java.util.Optional;

/**
 * @description:   https://www.cnblogs.com/zhangboyu/p/7580262.html
 * @author: shurunlong
 * @create: 2020-08-05 11:57
 */
public class Test1 {
    public static void main(String[] args) {
        User user = new User();

        System.out.println("======1=========");
        //java.util.NoSuchElementException: No value present
//        Optional<User> emptyOpt = Optional.empty();
//        System.out.println(emptyOpt.get());

        System.out.println("=======2=======");
        Optional<User> opt = Optional.of(user);
        //User(name=null)
        System.out.println(opt.get());

        System.out.println("=======3=======");
        Optional<User> opt1 = Optional.ofNullable(user);
        //User(name=null)
        System.out.println(opt1.get());

        System.out.println("=======4=======");
        Optional<User> opt2 = Optional.ofNullable(user);
        //true
        System.out.println(opt2.isPresent());

        System.out.println("=======5=======");

        Optional<User> opt3 = Optional.ofNullable(user);
        //usr存在
        opt3.ifPresent(u -> System.out.println("usr存在"));
        User user1=null;
        Optional<User> opt4 = Optional.ofNullable(user1);
        //什么都没打印   user 用户不为 null 的时候才会执行 ()中的代码
        opt4.ifPresent(u -> System.out.println("usr存在"));

        System.out.println("==========6===返回默认值====");

        User user3 = null;
        User user4 = new User("anna@gmail.com");
        //如果user3对象是空, 则将user4作为返回值
        User result = Optional.ofNullable(user3).orElse(user4);
        System.out.println(result);

        User user5= new User("user5");
        User user6 = new User("anna@gmail.com");
        //如果user5对象是空, 则将user4作为返回值
        User result1 = Optional.ofNullable(user5).orElse(user6);
        System.out.println(result1);


        System.out.println("==========7==================");

        User user7 = null;
        //当对象为空而返回默认对象时，行为并无差异
        User result3 = Optional.ofNullable(user7).orElse(createNewUser());
        System.out.println("Using orElse---"+result3);
        User result4 = Optional.ofNullable(user7).orElseGet(() -> createNewUser());
        System.out.println("Using orElseGet----"+result4);

        System.out.println("==========8==================");
        User user8 = new User("john@gmail.com");
        //两个 Optional  对象都包含非空值，两个方法都会返回对应的非空值。不过，orElse() 方法仍然创建了 User 对象。与之相反，orElseGet() 方法不创建 User 对象。
        //执行较密集的调用时，比如调用 Web 服务或数据查询，这个差异会对性能产生重大影响。挺鸡肋的, 总之 推荐使用  orElseGet()
        User result5 = Optional.ofNullable(user8).orElse(createNewUser());
        System.out.println("Using orElse---"+result5);
        User result6 = Optional.ofNullable(user8).orElseGet(() -> createNewUser());
        System.out.println("Using orElseGet----"+result6);

        System.out.println("========9=====返回异常=============");
        //orElseThrow() API —— 它会在对象为空的时候抛出异常，而不是返回备选的值
        //java.lang.IllegalArgumentException
        User user9 = null;
//        User result7 = Optional.ofNullable(user9).orElseThrow( () -> new IllegalArgumentException());


        System.out.println("=======10========转换值====");

        User user10 = new User("anna@gmail.com");
        String s = Optional.ofNullable(user).map(u -> u.getName()).orElse("default@gmail.com");
        System.out.println(s);
        System.out.println(user10);



    }

    private static User createNewUser() {
        System.out.println("Creating New User");
        return new User("extra@gmail.com");
    }
}
