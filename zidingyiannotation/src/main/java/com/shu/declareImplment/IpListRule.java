package com.shu.declareImplment;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
        validatedBy = {IpListValidator.class}
)
//@Repeatable(SpecialCharacterRule.List.class)
public @interface IpListRule {

    String message() default "IP format error";

    String id() default "0";

    String ip();

    String netDom();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    @Target({ElementType.TYPE,ElementType.METHOD, ElementType.FIELD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE,ElementType.TYPE_USE})
//    @Retention(RetentionPolicy.RUNTIME)
//    @Documented
//    @interface List {
//        SpecialCharacterRule[] value();
//    }
}