package edu.natu.systemuser.common.token;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ljiahai
 * @des 用于不需要验证token的方法上，例如验证码的获取等等
 * @date 2021-09-17 16:22:10
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JwtIgnore {
    boolean value() default true;
}
