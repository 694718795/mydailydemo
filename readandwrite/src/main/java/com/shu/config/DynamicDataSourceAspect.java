/**
 * 版权所有(C)，上海***股份有限公司，2018，所有权利保留。
 * 
 * 项目名：	springboot
 * 文件名：	DynamicDataSourceAspect.java
 * 模块说明：	
 * 修改历史：
 * 2018年10月21日 - Administrator - 创建。
 */
package com.shu.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 定义切面
 * 
 * @author Administrator
 *
 */
@Aspect
//@Order(-10)
@Component
public class DynamicDataSourceAspect implements Ordered {

  @Around("execution(public * com.shu.jpa.core..*.*(..))")
  public Object around(ProceedingJoinPoint pjp) throws Throwable {
    MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
    Method targetMethod = methodSignature.getMethod();
    if (targetMethod.isAnnotationPresent(TargetDateSource.class)) {
      String targetDataSource = targetMethod.getAnnotation(TargetDateSource.class).dataSource();
      System.out.println("----------数据源是:" + targetDataSource + "------");
      DynamicDataSourceHolder.setDataSource(targetDataSource);
    }

    // 执行方法
    Object result = pjp.proceed();
    DynamicDataSourceHolder.clearDataSource();
    return result;
  }

  @Override
  public int getOrder() {
    return -1;
  }
}
