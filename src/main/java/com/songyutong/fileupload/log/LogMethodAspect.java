package com.songyutong.fileupload.log;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 方法打印参数，结果日志增强
 *
 * @author ：songyutong
 * @date ：2024/1/25
 */
@Aspect
@Slf4j
@Component
public class LogMethodAspect {

    @Pointcut(value = "within(com.songyutong..*)")
    public void cutPoint() {
    }

    @Around("cutPoint() && @annotation(logMethod)")
    public Object aroundAdvice(ProceedingJoinPoint pjp, LogMethod logMethod) throws Throwable {
        boolean timed = logMethod.showTimeConsuming();
        long start = System.currentTimeMillis();
        // 类名
        String className = pjp.getTarget().getClass().getSimpleName();
        // 方法描述，若为空使用方法名
        String desc = logMethod.desc();
        String methodName = pjp.getSignature().getName();
        desc = StringUtils.isNotBlank(desc) ? desc : methodName;
//        log.info("执行{}({}#{})开始, 参数: {}", desc, className, methodName, JSON.toJSONString(pjp.getArgs()));
        try {
            Object result = pjp.proceed();
            if (timed) {
                log.info("执行{}({}#{})成功, 耗时{}ms: {}", desc, className, methodName, System.currentTimeMillis() - start, JSON.toJSONString(result));
            } else {
                log.info("执行{}({}#{})成功: {}", desc, className, methodName, JSON.toJSONString(result));
            }
            return result;
        } catch (Throwable t) {
            if (timed) {
                log.info("执行{}({}#{})异常, 耗时{}ms: {}", desc, className, methodName, System.currentTimeMillis() - start, t.getMessage());
            } else {
                log.info("执行{}({}#{})异常: {}", desc, className, methodName, t.getMessage());
            }
            throw t;
        }
    }
}
