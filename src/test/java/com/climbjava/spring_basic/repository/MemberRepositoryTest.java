package com.climbjava.spring_basic.repository;

import com.climbjava.spring_basic.domain.Member;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Slf4j
public class MemberRepositoryTest {
  @Autowired
  private MemberRepository memberRepository;

  @Test
  public void testExist(){
    log.info("{}", memberRepository);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testInsert(){
    Member member = Member.builder().id("sae").pw("1234").age(32).name("소똥이").build(); //
    memberRepository.save(member);
  }

  @Test
  public void testFindById(){
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없습니다."));
    Member member1;
  }

  @Test
  public void testFindAll() {
    memberRepository.findAll().forEach(member -> log.info("{}", member));
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate(){
    Member member = memberRepository.findById(1L).orElseThrow(() -> new RuntimeException("지정된 회원 번호가 없습니다."));
    member.setAge(20);
    log.info("{}", member);
  }

  @Test
  public void testDelete(){
    memberRepository.deleteById(2L);
  }
}
