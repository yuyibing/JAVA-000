package com.work7.mutlidatasource.aspectj;

import com.work7.mutlidatasource.holder.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Aspect
@Component
public class DatasourceChooseAspect {
//    private static final log log = logFactory.getlog(DatasourceChooseAspect.class);

    private final String[] QUERY_PREFIX = {"query"};

//    @Pointcut("execution( * com.work7.*.service.impl.*ServiceImpl.*(..))")
    @Pointcut("@annotation(com.work7.mutlidatasource.annotation.ReadOnly)")
    public void daoAspect() {
    }

    @Before("daoAspect()")
    public void switchDataSource(JoinPoint point) {
        Object[] params = point.getArgs();
        System.out.println(params.toString());
        Object param = params[0];
        for (Object string:params) {
            log.info(string.toString());
        }
        log.info("###################################################");
        log.info(point.getSignature().getName());
//        Boolean isQueryMethod = isQueryMethod(point.getSignature().getName());
        Boolean isQueryMethod = true;
        if (isQueryMethod) {
            String slave = loadBalance();
            DynamicDataSourceContextHolder.setDataSourceKey(slave);
            log.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    private String loadBalance() {
        Random random = new Random();
        final int i = random.nextInt(2) + 1;
        log.info("load balance slave" + i);
        return "slave" + i;
    }

    @After("daoAspect())")
    public void restoreDataSource(JoinPoint point) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
        log.info("Restore DataSource to [{}] in Method [{}]",
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }

    private Boolean isQueryMethod(String methodName) {
        for (String prefix : QUERY_PREFIX) {
            if (methodName.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }
}