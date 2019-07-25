package dong.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * @author dong
 * @Title: ServiceLogAspect
 * @ProjectName mytest
 * @Description: TODO
 * @date 2019/5/15下午 2:56
 */
@Aspect
@Component
public class ServiceLogAspect {

    @Pointcut("execution(* dong.service.*.*(..))")
    public void beforPointcut(){
    }

    @Around("execution(* dong.service.*.*(..))")
    public Object befor(ProceedingJoinPoint point) throws Throwable {
        Object[] params = point.getArgs();
        Object taget = point.getTarget();
        String methedName = point.getSignature().getName();
        String[] aa = ((MethodSignature) point.getSignature()).getParameterNames();
        Method methed = getMethodByClassAndName(taget.getClass(), methedName);
        Parameter[] param = methed.getParameters();
        System.out.println("前置通知");
        Object obj = point.proceed();
        System.out.println("后置通知");
        return obj;
    }

    /**
     * 根据类和方法名得到方法
     */
    public Method getMethodByClassAndName(Class c , String methodName){
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method ;
            }
        }
        return null;
    }
}
