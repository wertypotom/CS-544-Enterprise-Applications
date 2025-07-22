package customers;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Aspect
@Configuration
public class LoggingAdvice {
    @After("execution(* customers.EmailSender.sendEmail(..))")
    public void logAfter(JoinPoint joinpoint) {
        Object[] args = joinpoint.getArgs();
        EmailSender outgoingMailServer = (EmailSender)joinpoint.getTarget();
        System.out.println(LocalDateTime.now() + " method="+joinpoint.getSignature().getName() + " address=" + args[0] + " massage=" + args
        [1] + " outgoing mail server " + outgoingMailServer.outgoingMailServer);
    }
}
