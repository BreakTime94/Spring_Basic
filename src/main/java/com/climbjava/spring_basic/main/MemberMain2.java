package com.climbjava.spring_basic.main;

import com.climbjava.spring_basic.domain.Member;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MemberMain2 {
  public static void main(String[] args) {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/bean-config-java.xml");
    Member m = context.getBean("member", Member.class);
    System.out.println(m);
    //2개는 같은 객체다?
    Member m2 = context.getBean("member", Member.class);
    System.out.println(m2);
  }
}
