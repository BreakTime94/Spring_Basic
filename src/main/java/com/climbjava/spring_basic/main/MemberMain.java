package com.climbjava.spring_basic.main;

import com.climbjava.spring_basic.domain.Member;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/bean-config.xml");
    Member m = context.getBean("member", Member.class);
    Member m2 = context.getBean("member", Member.class);
    System.out.println(m == m2);
  }
}
