package com.climbjava.spring_basic.repository;

import com.climbjava.spring_basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
  void deleteByMemoText(String memoText);
}
