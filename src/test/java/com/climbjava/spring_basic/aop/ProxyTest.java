package com.climbjava.spring_basic.aop;

import com.climbjava.spring_basic.aop.advice.MyBeforeAdvice;
import com.climbjava.spring_basic.aop.advice.MyAfterReturn;
import com.climbjava.spring_basic.aop.pointcut.MySimplePointcut;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.aop.Advice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ProxyTest {
  @Autowired
  private BoardService boardService;
  @Autowired
  private LoggingAdvice advice;
  @Autowired
  private MyBeforeAdvice before;
  @Autowired
  private MyAfterReturn afterReturn;
  @Autowired
  private ThrowsAdvice throwsAdvice;

  @Autowired
  private MySimplePointcut pointcut;

  private BoardService proxy;

  @PostConstruct
  public void init() {
    Advice[] advices = new Advice[]{afterReturn, throwsAdvice};
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    for(Advice a : advices) {
      proxyFactory.addAdvice(a);
    }
    proxy = (BoardService) proxyFactory.getProxy();
  }

  @Test
  public void testExist(){
    log.info("{}", boardService);
  }

  @Test
  public void testWrite(){
    boardService.write("원본 객체의 제목", "내용");

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    BoardService proxy = (BoardService) proxyFactory.getProxy();

    proxy.write("프록시 객체의 제목", "내용");
  }

  @Test
  public void testBefore() {
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvice(advice);
    proxyFactory.addAdvice(before);
    BoardService proxy = (BoardService) proxyFactory.getProxy();

    System.out.println("========================구분선=====================");
    System.out.println("========================글쓰기=====================");
    proxy.write("프록시 객체의 제목", "내용");
    System.out.println("========================글조회=====================");
    proxy.findBy(130L);
    proxy.findBy(1L);
    System.out.println("========================글수정=====================");
    proxy.modify("수정 제목", "수정 내용");
    System.out.println("========================글삭제=====================");
    proxy.delete(130L);
    proxy.delete(1L);
  }

  @Test
  public void testAfterReturn() {
    try{
      proxy.delete(1L);
    }
    catch (Exception e){
      System.out.println(e.getMessage());
    }
  }

  @Test
  public void testAdvisor(){
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);

    // Advisor
    PointcutAdvisor pointcutAdvisor = new DefaultPointcutAdvisor(pointcut, before);
    proxyFactory.addAdvisor(pointcutAdvisor);

    proxy = (BoardService) proxyFactory.getProxy();

    proxy.write("제목", "내용");
    proxy.findBy(3L);
    proxy.delete(3L);

  }

  @Test
  public void testAspectj() {
    AspectJExpressionPointcut pc = new AspectJExpressionPointcut();
    pc.setExpression("execution(void *.write*(..))");
    DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pc, before);

    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(boardService);
    proxyFactory.addAdvisor(advisor);
    proxy = (BoardService) proxyFactory.getProxy();
    proxy.write("title", "content");
    proxy.findBy(3L);
  }

}
