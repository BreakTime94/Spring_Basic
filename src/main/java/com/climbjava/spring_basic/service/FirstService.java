package com.climbjava.spring_basic.service;

import com.climbjava.spring_basic.aop.LoggingAdvice;
import com.climbjava.spring_basic.aop.advice.MyAfterReturn;
import com.climbjava.spring_basic.aop.advice.MyBeforeAdvice;
import com.climbjava.spring_basic.aop.pointcut.MySimplePointcut;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FirstService { //FirstClass의 two 메서드만 advice가 적용되도록 pointcut 정의 (before advice)

  public void one(){

    System.out.println("First.one()");
  }
  public void two(){
    System.out.println("First.two()");
  }
}
