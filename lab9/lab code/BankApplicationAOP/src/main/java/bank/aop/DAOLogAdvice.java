package bank.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DAOLogAdvice {
    @Autowired
    private ILogger logger;

    @After("execution(* bank.dao.*.*(..))")
    public void logDAOCall(JoinPoint joinpoint) {
        System.out.println("👨🏼‍💻Called dao log method="+joinpoint.getSignature().getName());
    }
}
