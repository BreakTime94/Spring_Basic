package com.climbjava.spring_basic.aop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Proxy;

@Slf4j
public class App {
  public static void main(String[] args) {
    BoardService target = new BoardServiceImpl();
    System.out.println("==============target의 결과물===============");
    target.write("제목", "내용");

    BoardService proxy = (BoardService) Proxy.newProxyInstance(
            BoardService.class.getClassLoader(),
            new Class[] {BoardService.class},
            new LoggingInvocationHandler(target)
    );

    System.out.println("==============proxy의 결과물====================");
    proxy.write("title","content");


  }
}
