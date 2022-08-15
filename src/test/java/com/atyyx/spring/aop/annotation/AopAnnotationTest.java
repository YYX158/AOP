package com.atyyx.spring.aop.annotation;

import com.atyyx.spring.aop.annoatation.Calculator;
import com.atyyx.spring.aop.annoatation.impl.CalculatorImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopAnnotationTest {
    @Test
    public void testAOPByAnnocatation()
    {
        ApplicationContext ioc=new ClassPathXmlApplicationContext("aop-annotation.xml");
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.div(1,2);
    }
}
