package com.climbjava.spring_basic.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "tbl_memo")
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Memo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mno;
  @Column(nullable = false)
  private String memoText = "기본값";
}
