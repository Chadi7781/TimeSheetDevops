
package tn.esprit.spring.aop;

import java.lang.reflect.Method;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Aspect
@Configuration
@EnableAspectJAutoProxy
public class ExecutionTimeAspect {
	
	Logger log=LoggerFactory.getLogger(ExecutionTimeAspect.class);
	
    @Around("@annotation(tn.esprit.spring.aop.TrackTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        TrackTime measured = method.getAnnotation(TrackTime.class);
        String message = measured.message();
            log.debug("Method {} execution: {} ms",message , executionTime);
            return proceed;
       
    }
}