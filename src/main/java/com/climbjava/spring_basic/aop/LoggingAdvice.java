package com.climbjava.spring_basic.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

@Component
public class LoggingAdvice implements MethodInterceptor {
  // invoke 촉발하다, 불러 일으키다
  @Override
  public Object invoke(MethodInvocation methodInvocation) throws Throwable {
    System.out.println("[로그] 호출 전 :: " + methodInvocation.getMethod().getName());
    Object o = methodInvocation.proceed();
    System.out.println("[로그] 호출 전 :: " + methodInvocation.getMethod().getName());
    return o;
  }
}
