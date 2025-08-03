package bank.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StopWatch;

@Aspect
@Configuration
public class StopWatchAdvice {
    @Around("execution(* bank.service.*.*(..))")
    public Object methodTimer(ProceedingJoinPoint call ) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totalTime = sw.getLastTaskTimeMillis();
        System.out.println("🚀 Call to bank service method " + call.getSignature().getName() + " took=" + totalTime + " ms");
        return retVal;
    }

    @AfterReturning(pointcut="execution(* bank.service.*.*(..))", returning="retValue")
    public void outputResult(JoinPoint joinpoint, Object retValue) {
        System.out.println("🚀💥Return value from bank service ="+retValue);
    }
}
