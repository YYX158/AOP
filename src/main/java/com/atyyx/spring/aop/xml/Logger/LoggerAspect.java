package com.atyyx.spring.aop.xml.Logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component

public class LoggerAspect {



    public void beforeAdviceMethod(JoinPoint joinPoint)
    {
        // 获取连接点所对应方法的签名信息（签名信息：方法的声明信息: public void beforeAdviceMethod(JoinPoint joinPoint)
        Signature signature = joinPoint.getSignature();
        // 通过连接点来获取连接点对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect,方法名"+signature.getName()+",方法的参数是:"+ Arrays.toString(args));
    }


    public void afterAdviceMehthod(JoinPoint joinPoint)
    {
        // 获取连接点所对应方法的签名信息（签名信息：方法的声明信息: public void beforeAdviceMethod(JoinPoint joinPoint)
        Signature signature = joinPoint.getSignature();
        // 通过连接点来获取连接点对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect,后置通知"+signature.getName()+"执行完毕");
    }


    public void afterRunringAdviceMethod(JoinPoint joinPoint,Object result)
    {
        // 先获取连接点的签名信息
        Signature signature = joinPoint.getSignature();
        // 获取返回值信息

        System.out.println("LoggerAspect,方法:"+signature.getName()+"的返回结果是:"+result);
    }


    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable exception)
    {
        // 先获取连接点的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect，方法:"+signature.getName()+",异常:"+exception);
    }


    public Object aroundAdviceMethod(ProceedingJoinPoint joinPoint)
    {

        Object result=null;
        try
        {
            System.out.println("环绕通知----->前置通知");
            //表示目标对象方法的执行
            result = joinPoint.proceed();
            System.out.println("环绕通知----->返回通知");
        }
        catch (Throwable throwable)
        {
            System.out.println("环绕通知----->异常通知");
            throwable.printStackTrace();
        }
        finally
        {
            System.out.println("环绕通知----->后置通知");
        }
        return result;
    }

}
