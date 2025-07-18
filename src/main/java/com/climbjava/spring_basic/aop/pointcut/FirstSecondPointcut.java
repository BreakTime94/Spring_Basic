package com.climbjava.spring_basic.aop.pointcut;

import com.climbjava.spring_basic.service.FirstService;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class FirstSecondPointcut extends StaticMethodMatcherPointcut {
  @Override
  public boolean matches(Method method, Class<?> targetClass) {
    //targetClass.getName().equals("com.climbjava.spring_basic.service.FirstService")
    // targetClass.equals(FirstService.class);
    return method.getName().equals("two") && targetClass == (FirstService.class);

  }
}
