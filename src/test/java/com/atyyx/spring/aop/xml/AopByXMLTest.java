package com.atyyx.spring.aop.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopByXMLTest {

    @Test
    public void testAop()
    {
        ApplicationContext ioc=new ClassPathXmlApplicationContext("aop-xml.xml");
        Calculator calculator = ioc.getBean(Calculator.class);
        calculator.add(2,4);
    }
}
