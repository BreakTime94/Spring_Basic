package com.climbjava.spring_basic.repository;

import com.climbjava.spring_basic.domain.Memo;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Slf4j
public class MemoRepositoryTest {
  @Autowired
  private MemoRepository memoRepository;

  @Autowired
  private EntityManager em;

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager() {
    log.info("testEntityManager :: {}", em);
    em.persist(new Memo());
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager2() {
//    Memo memo = memoRepository.findById(2L).orElseThrow(RuntimeException::new);
//    memo.setMemoText("Hello World");
    Memo memo = em.find(Memo.class, 1L); // 영속 객체
    memo.setMemoText("안녕 세상");
  }
  //JPA는 dirty checking을 통해 값 변경을 감지한다.

  @Test
  @Transactional
  @Rollback(false)
  public void testEntityManager3() {
    Memo memo = new Memo();
    memo.setMno(2L);
    memo.setMemoText("비영속");

    em.merge(memo); //원래는 merge를 해야한다.
  }

  @Test
  public void testExist(){
    log.info("{}", memoRepository);
  }

  @Test
  public void testInsert() {
    memoRepository.save(new Memo());
  }

  @Test
  public void testDelete() {
    Memo memo = em.find(Memo.class, 2L);
    memoRepository.delete(memo);
  }

  @Test
  public void testFindByMno() {

  }
}
