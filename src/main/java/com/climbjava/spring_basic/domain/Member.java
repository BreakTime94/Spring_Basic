package com.climbjava.spring_basic.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
//@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
  //@Value("개똥이")
  private String name;
  //@Value("32")
  private int age;

  private String id;

  public Member(String s, int i) {
    this.name = s;
    this.age = i;
  }
}
