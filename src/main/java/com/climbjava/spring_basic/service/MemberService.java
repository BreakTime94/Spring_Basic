package com.climbjava.spring_basic.service;

import com.climbjava.spring_basic.domain.Member;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public interface MemberService {

  void register(Member member);
  //Setter Injection
//  @Autowired
//  public void setMember(Member member){
//    this.member = member;
//  }
}
