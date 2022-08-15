package com.atyyx.spring.aop.annoatation.Logger;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;

/**
 * 通知的类型
 * 前置通知
 * 返回通知
 * 后置通知
 * 异常通知
 * 环绕通知：相当于上面四种通知的总和
 * 在切面中，需要通过制定的注解将方法表示为通知方法
 * @Before：前置通知，在目标方法执行之前执行
 * @Ater：后置通知，是在finally中输出的
 * @AfterReturning 返回通知，在目标对象返回值之后才执行
 * @AfterThrowing在出现异常的时候才会执行，就是在catch中执行  可以用throwing = "exception"指明异常的具体信息
 */
@Component
@Aspect  // 将当前组件表示为切面
public class LoggerAspect {

    /**
     * 可以通过设置公共切点表达式来进行引用切入点表达式
     */
    @Pointcut("execution(* com.atyyx.spring.aop.annoatation.impl.CalculatorImpl.*(..))")
    public void pointCut()
    {
    }
    //@Before(value = "execution(public int com.atyyx.spring.aop.annoatation.impl.CalculatorImpl.add(int,int))")
    //不在意修饰符的时候，一般常这么写  用*表示是任意的访问修饰符和返回值类型   用全类名.*表示类中的所有方法  然后写一个括号表示方法的参数
    //..表示任意的方法中的任意的参数个数
    // 在类的位置写一个*表示在在这个包里面的
    //事务一般都写在Server层上
    // 切入点表达式：设置在表示通知的注解的value属性中
    @Before("execution(* com.atyyx.spring.aop.annoatation.impl.CalculatorImpl.*(..))")
    public void beforeAdviceMethod(JoinPoint joinPoint)
    {
        // 获取连接点所对应方法的签名信息（签名信息：方法的声明信息: public void beforeAdviceMethod(JoinPoint joinPoint)
        Signature signature = joinPoint.getSignature();
        // 通过连接点来获取连接点对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect,方法名"+signature.getName()+",方法的参数是:"+ Arrays.toString(args));
    }



    @After("pointCut()") // 切入点表达式的重用
    public void afterAdviceMehthod(JoinPoint joinPoint)
    {
        // 获取连接点所对应方法的签名信息（签名信息：方法的声明信息: public void beforeAdviceMethod(JoinPoint joinPoint)
        Signature signature = joinPoint.getSignature();
        // 通过连接点来获取连接点对应的方法的参数
        Object[] args = joinPoint.getArgs();
        System.out.println("LoggerAspect,后置通知"+signature.getName()+"执行完毕");
    }

    //result就是用来接收目标对象方法的返回值的参数名
    @AfterReturning(value = "pointCut()",returning = "result")
    public void afterRunringAdviceMethod(JoinPoint joinPoint,Object result)
    {
        // 先获取连接点的签名信息
        Signature signature = joinPoint.getSignature();
        // 获取返回值信息

        System.out.println("LoggerAspect,方法:"+signature.getName()+"的返回结果是:"+result);
    }

    @AfterThrowing(value = "pointCut()",throwing = "exception")
    public void afterThrowingAdviceMethod(JoinPoint joinPoint, Throwable exception)
    {
        // 先获取连接点的签名信息
        Signature signature = joinPoint.getSignature();
        System.out.println("LoggerAspect，方法:"+signature.getName()+",异常:"+exception);
    }

    @Around("pointCut()")
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
