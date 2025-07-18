package com.climbjava.spring_basic.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class MyAroundAdvice implements MethodInterceptor {

  public Object invoke(MethodInvocation mi) throws Throwable {

    Object o = mi.proceed();

    return o;
  }
}
