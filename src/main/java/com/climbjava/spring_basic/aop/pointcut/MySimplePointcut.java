package com.climbjava.spring_basic.aop.pointcut;

import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class MySimplePointcut extends StaticMethodMatcherPointcut {
  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    // 매개변수 갯수가 1개 그리고 리턴타입이 void인 조건식 만들기
    return (method.getParameterCount() == 1) && (method.getReturnType() == void.class);
  }
}
