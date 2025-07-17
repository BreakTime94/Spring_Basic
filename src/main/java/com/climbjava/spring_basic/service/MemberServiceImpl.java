package com.climbjava.spring_basic.service;

import com.climbjava.spring_basic.domain.Member;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("mem1")
@ToString
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
  @NonNull
  private Member member;


//  MemberServiceImpl(Member member) {
//    this.member = member;
//  }

  @Override
  public void register(Member member) {

  }
}
