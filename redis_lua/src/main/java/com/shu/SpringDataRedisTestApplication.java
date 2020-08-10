package com.shu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
*  参考博客:
*  https://github.com/shengchaojie/Spring-data-redis-demo
*  https://www.jianshu.com/p/366d1b4f0d13
*
* 2020-8月7号,钟帅提出一个并发情况中对redis中 一个指定key下的value(json对象) 中的一个值进行 修改.
*  其value是json  要在redis中对json进行操作需要用到 cjson.
*  为了保证原子性, 因此用到一个 redis+lua+cjson的组合
*
* 个人理解:  lua相当于一个函数,  当对redis进行操作时, redis内部调用这个函数, 由于redis内部单线程的设计,就保证这个函数中这部分功能是原子性的,也相当于事务
*
* 使用lua 相较于  在java调用端通过加锁来实现要性能更好, 也更加轻便.
 *
* */
@SpringBootApplication
public class SpringDataRedisTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisTestApplication.class, args);
	}
}
