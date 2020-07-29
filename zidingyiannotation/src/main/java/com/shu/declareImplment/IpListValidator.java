package com.shu.declareImplment;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class IpListValidator implements ConstraintValidator<IpListRule, Object> {

    private List<String> key_list = Arrays.asList("ip", "mask", "primary");
    private String id;
    private String ip;
    private String netDom;

    @Override
    public void initialize(IpListRule constraintAnnotation) {
        this.id = constraintAnnotation.id();
        this.ip = constraintAnnotation.ip();
        this.netDom = constraintAnnotation.netDom();
    }


    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (null == o) {
            return false;
        }
        BeanWrapper beanWrapper = new BeanWrapperImpl(o);

        Object id = beanWrapper.getPropertyValue(this.id);
        Object ip = beanWrapper.getPropertyValue(this.ip);
        Object netDom = beanWrapper.getPropertyValue(this.netDom);




        return false;
    }

}