package com.climbjava.spring_basic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_board")
public class Board {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long bno;
  @Column(length = 1000, nullable = true)
  private String title;

  @Column(columnDefinition = "text")
  private String content;

  private LocalDateTime regdate;
  //작성자 정보
  @ManyToOne
  @JoinColumn(name = "user_no")
  private Member member;
}
