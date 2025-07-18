package com.climbjava.spring_basic.service;

import com.climbjava.spring_basic.aop.LoggingAdvice;
import com.climbjava.spring_basic.aop.advice.MyAfterReturn;
import com.climbjava.spring_basic.aop.advice.MyBeforeAdvice;
import com.climbjava.spring_basic.aop.pointcut.FirstSecondPointcut;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@SpringBootTest
@Slf4j
public class FirstSecondServiceTest {
  @Autowired
  private FirstService firstService;
  @Autowired
  private SecondService secondService;
  @Autowired
  private LoggingAdvice advice;
  @Autowired
  private MyBeforeAdvice before;
  @Autowired
  private MyAfterReturn afterReturn;
  @Autowired
  private ThrowsAdvice throwsAdvice;
  @Autowired
  private FirstSecondPointcut pointcut;

  private FirstService firstProxy;

  private SecondService secondProxy;

  @Test
  public void testExist(){
    log.info("{} {}", firstService, secondService);
  }

  @Test
  public void firstAdviceTest() {
    ProxyFactory pf1 = new ProxyFactory();
    pf1.setTarget(firstService);
    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, before);
    pf1.addAdvisor(pointcutAdvisor);

    firstProxy = (FirstService) pf1.getProxy();

    firstProxy.one();

    firstProxy.two();

  }

  @Test
  public void secondAdviceTest() {
    ProxyFactory pf2 = new ProxyFactory();

    pf2.setTarget(secondService);

    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, before);
    pf2.addAdvisor(pointcutAdvisor);

    secondProxy = (SecondService) pf2.getProxy();

    secondProxy.one();

    secondProxy.two();
  }

  @Test
  public void firstSecondAdviceTest() {

    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(new StaticMethodMatcherPointcut() {
      @Override
      public boolean matches(Method method, Class<?> targetClass) {
        return method.getName().equals("two") && targetClass == (FirstService.class);
      }
    }, new MethodBeforeAdvice() {
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("익명 출력");
      }
    });

    ProxyFactory pf1 = new ProxyFactory();
    pf1.setTarget(firstService);
    pf1.addAdvisor(pointcutAdvisor);

    ProxyFactory pf2 = new ProxyFactory();
    pf2.setTarget(secondService);
    pf2.addAdvisor(pointcutAdvisor);

    firstProxy = (FirstService) pf1.getProxy();
    secondProxy = (SecondService) pf2.getProxy();

    firstProxy.one();
    firstProxy.two();

    secondProxy.one();
    secondProxy.two();
  }
}
