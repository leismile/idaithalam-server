package ch.inss.idaiserver.config;

import java.time.LocalDateTime;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;;

@Configuration
@Aspect
public class LoggerAspect {

    @Before("@annotation(ch.inss.idaiserver.config.MethodLogger)")
    public void beforeMethodStart(JoinPoint point) {
        //final Logger logger = LoggerFactory.getLogger(point.getClass());
        System.out.println("Method " + point.getSignature().getName() + " Started at " + LocalDateTime.now());
        //logger.info("Logging: Method " + point.getSignature().getName() + " Started at " + LocalDateTime.now());
        
        
       // System.out.println("Value: " + point.getClass().getAnnotation(MethodLogger.class).value());

    }

    @After("@annotation(ch.inss.idaiserver.config.MethodLogger)")
    public void afterMethodStart(JoinPoint point) {
        System.out.println("\nMethod " + point.getSignature().getName() + " Ended at " + LocalDateTime.now());

    }


}