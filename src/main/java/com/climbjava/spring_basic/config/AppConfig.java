package com.climbjava.spring_basic.config;

import com.climbjava.spring_basic.domain.Board;
import com.climbjava.spring_basic.domain.Member;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class AppConfig {
//  @Bean
//  public Member member(){
//    return new Member("소똥이", 22);
//  }
//  @Bean
//  @ConfigurationProperties("spring.datasource.hikari")
//  public HikariConfig hikariConfig(){
//    return new HikariConfig();
//  }
//  @Bean
//  public HikariDataSource hikariDataSource(){
//    return new HikariDataSource(hikariConfig());
//  }
}
