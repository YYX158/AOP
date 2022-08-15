package com.atyyx.spring.aop.annoatation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1) // 可以通过@Order注解的value属性值设置优先级，默认值是Integer的最大值
//@Order注解的value属性值越小，表示优先级越高
public class ValidateAspect
{
//    @Pointcut("execution(* com.atyyx.spring.aop.annoatation.impl.CalculatorImpl.*(..))")
//    public void pointCut()
//    {}

    @Before("com.atyyx.spring.aop.annoatation.Logger.LoggerAspect.pointCut()")
    public void beforeMethod()
    {
        System.out.println("ValidateAspect--------->前置通知");
    }
}
