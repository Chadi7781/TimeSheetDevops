
package tn.esprit.spring.aop;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



@Aspect
@Configuration
@EnableAspectJAutoProxy
public class ExecutionTimeAspect {
	
	private static final Logger log = LogManager.getLogger(ExecutionTimeAspect.class);
	
    @Around("@annotation(tn.esprit.spring.aop.TrackTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        TrackTime measured = method.getAnnotation(TrackTime.class);
        String message = measured.message();
        if (Strings.isEmpty(message))
            log.debug("Method {} execution: {} ms",message , executionTime);
        else
            log.debug("{}: {} ms", message, executionTime);
        return proceed;
    }
}