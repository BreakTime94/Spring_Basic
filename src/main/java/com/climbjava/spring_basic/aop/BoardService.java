package com.climbjava.spring_basic.aop;

import com.climbjava.spring_basic.domain.Board;

public interface BoardService {
  void write(String title, String content);
  Object findBy(Long bno);
  void modify(String title, String content);
  void delete(Long bno);

}
