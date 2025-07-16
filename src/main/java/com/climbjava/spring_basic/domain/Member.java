package com.climbjava.spring_basic.domain;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Member {
  @Value("개똥이")
  private String name;
  @Value("32")
  private int age;
}
