package com.shu.aopimplement;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Objects;

/**
 * @description:
 * @author: shurunlong
 * @create: 2020-07-17 16:36
 */
@Aspect
@Component
public class Aop {

    @Autowired
    private AESEncrypt aesEncrypt;

    @Pointcut("@annotation( com.shu.aopimplement.PrivateDataMethod)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        Object responseObj = null;
        try {
            Object[] request = joinPoint.getArgs();
            for (Object object : request) {
                if (object instanceof Collection) {
                    Collection collection = (Collection) object;
                    collection.forEach(var -> {
                        try {
                            handle(var,type.ENCRYPT);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    });
                } else {
                    handle(object, type.ENCRYPT);
                }
            }
            responseObj = joinPoint.proceed();
            if (responseObj instanceof Collection) {
                Collection collection = (Collection) responseObj;
                collection.forEach(var -> {
                    try {
                        handle(var,type.DECRYPT);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                handle(responseObj, type.DECRYPT);
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
//            log.error("SecureFieldAop 异常{}", throwable);
        }
        return responseObj;
    }

    enum type{
        DECRYPT,
        ENCRYPT
    }

    private void handle(Object requestObj, Enum typeEnum) throws IllegalAccessException {
        if (Objects.isNull(requestObj)) {
            return;
        }
        Field[] fields = requestObj.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(PrivateData.class);
            if (hasSecureField) {
                boolean accessible = field.isAccessible();
                if (!accessible) {
                    field.setAccessible(true);
                }
                String plaintextValue = (String) field.get(requestObj);
                String value = null;
                if(typeEnum.equals(type.DECRYPT)) {

                    value = AESEncrypt.desEncrypt(plaintextValue,"1111111111111111");
                }else if(typeEnum.equals(type.ENCRYPT)){
                    value = AESEncrypt.encrypt(plaintextValue,"1111111111111111");
                }
                field.set(requestObj, value);
                if (!accessible) {
                    field.setAccessible(false);
                }
            }
        }
    }

}
