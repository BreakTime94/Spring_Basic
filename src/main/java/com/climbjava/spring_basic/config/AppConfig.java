package com.climbjava.spring_basic.config;

import com.climbjava.spring_basic.domain.Member;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  public Member member(){
    return new Member("소똥이", 22);
  }
}
