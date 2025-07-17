package com.climbjava.spring_basic.aop;

import com.climbjava.spring_basic.domain.Board;
import org.springframework.stereotype.Service;

@Service
public class BoardServiceImpl implements BoardService {

  @Override
  public void write(String title, String content) {
    System.out.println(title + ", " + content);
    System.out.println("글쓰기 완료");

  }

  @Override
  public Object findBy(Long bno) {
    System.out.println(bno + "번 글을 가져옵니다.");
    return null;
  }

  @Override
  public void modify(String title, String content) {
    System.out.println(title + ", " + content);
    System.out.println("글 수정 완료");
  }

  @Override
  public void delete(Long bno) { //글 삭제할 떄 1번 글이면 예외를 던지게 해보자

    if(bno == 1L) {
      throw new RuntimeException("1번 게시글 안 받음 ㅋ");
    }
    System.out.println(bno + "번 글 삭제완료");

  }
}
