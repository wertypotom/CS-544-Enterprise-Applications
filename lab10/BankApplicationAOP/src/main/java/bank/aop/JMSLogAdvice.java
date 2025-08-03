package bank.aop;

import bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class JMSLogAdvice {
    @Autowired
    private ILogger logger;

    @After("execution(* bank.jms.*.sendJMSMessage(..))")
    public void logJMSMessage(JoinPoint joinpoint) {
        Object[] args = joinpoint.getArgs();

        logger.log("✉️Sent JMS message ="+args[0]);
    }
}
