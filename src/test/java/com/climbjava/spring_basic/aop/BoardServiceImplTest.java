package com.climbjava.spring_basic.aop;

import com.climbjava.spring_basic.SpringBasicApplication;
import com.climbjava.spring_basic.config.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BoardServiceImplTest {

  @Autowired
  BoardService boardService;

  @Test
  void write() {
    boardService.write("title", "content");
  }

  @Test
  void findBy() {
    boardService.findBy(1L);
  }

  @Test
  void modify() {
    boardService.modify("수정제목", "수정내용");
  }

  @Test
  void delete() {
    boardService.delete(3L);
  }
}