package com.climbjava.spring_basic.repository;

import com.climbjava.spring_basic.domain.Memo;
import com.climbjava.spring_basic.domain.dto.MemoDTO;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.stream.IntStream;

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
    IntStream.rangeClosed(1, 100).forEach(i -> {
      Memo memo = Memo.builder().memoText("sample..." + i).build();
      memoRepository.save(memo);
    });
  }

  @Test
  public void testSelectOne() {
    Memo memo = memoRepository.findById(5L).orElseThrow(() -> new RuntimeException("입력하신 메모번호가 없습니다."));
    log.info("{}", memo);
  }

  @Test
  @Transactional
  @Rollback(false)
  public void testUpdate() {
    Memo memo =  memoRepository.findById(1L).orElseThrow(() -> new RuntimeException("입력하신 메모번호가 없습니다."));
    memo.setMemoText("배가 매우 매우 고프다");
    //memoRepository.save(memo);
  }

  @Test
  public void testDelete() {
    Memo memo = memoRepository.findById(2L).orElseThrow(() -> new RuntimeException("입력하신 메모번호가 없습니다."));
    memoRepository.delete(memo);
  }

  @Test
  public void testPageDefault() {
    PageRequest pageRequest = PageRequest.of(10, 10);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach(m -> log.info("{}", m));
    long totalElements =  result.getTotalElements();
    int totalPages = result.getTotalPages();

    log.info("totalElements :: {}", totalElements);
    log.info("totalPages :: {}", totalPages);

    // 현재 페이지 번호
    log.info("getNumber :: {}", result.getNumber());
    // 페이지당 데이터 갯수
    log.info("getSize :: {}", result.getSize());
    //다음페이지 여부
    log.info("hasNext :: {}", result.hasNext());
    //시작페이지 여부
    log.info("isFirst :: {}", result.isFirst());
    //마지막페이지 여부
    log.info("isLast :: {}", result.isLast());

  }

  @Test
  public void testSort() {
    Sort sort = Sort.by("mno").descending();
    PageRequest pageRequest = PageRequest.of(0, 5, sort);
    Page<Memo> result = memoRepository.findAll(pageRequest);
    result.forEach(m -> log.info("{}", m));
  }

  @Test
  public void testQueryMethod1() {
    memoRepository.findByMnoBetweenOrderByMnoDesc(70L, 80L).forEach(m -> log.info("{}", m));
  }

  @Test
  public void testQueryMethod2() {
    Page<Memo> memos = memoRepository.findByMnoBetween(0L, 50L, PageRequest.of(0, 10, Sort.by("mno").descending()));
    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  public void testQueryMethod3() {
    log.info("{}", memoRepository.count());
  }

  @Test
  public void testQueryMethod4() {
    List<Memo> memos = memoRepository.findByMnoOrMemoText(1L, "배가 매우 고프다");

    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  public void testQueryMethod5() {
    List<Memo> memos = memoRepository.getListDesc();
    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  public void testQueryMethod6() {
    List<Memo> memos = memoRepository.getListDesc2();
    memos.forEach(m -> log.info("{}", m));
  }

  @Test
  public void testUpdateMemoTest1() {
    memoRepository.updateMemoText(3L, "변경내용");
  }

  @Test
  public void testUpdateMemoTest2() {
    memoRepository.updateMemoText2(Memo.builder().mno(4L).memoText("변경내용내용").build());
  }

  @Test
  public void testUpdateMemoTest3() {
    memoRepository.updateMemoText3(6L, "rollback 과 커밋과 transactional의 의미란?");
  }

  @Test
  public void testListWithQueryObject() {
    Page<Object[]> objects = memoRepository.getListWithQueryObject(7L, PageRequest.of(0, 10, Sort.by("mno").descending()));
    objects.forEach(o -> {
      for(Object m : o) {
        log.info("{}", m);
      }
    });
  }

  @Test
  public void testListWithQueryProjection() {
    Page<MemoDTO> dtos = memoRepository.getListWithQueryProjection(0L, PageRequest.of(0, 10, Sort.by("mno").descending()));
    dtos.forEach(d -> log.info("mno :: {} memoText :: {} n :: {}", d.getMno(), d.getMemoText(), d.getN()));
  }

}
