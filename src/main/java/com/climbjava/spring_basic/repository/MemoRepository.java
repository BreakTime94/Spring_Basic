package com.climbjava.spring_basic.repository;

import com.climbjava.spring_basic.domain.Memo;
import com.climbjava.spring_basic.domain.dto.MemoDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
  void deleteByMemoText(String memoText);
  List<Memo> findByMemoText(String memoText);

  List<Memo> findByMnoBetweenOrderByMnoDesc(Long mno1, Long mno2);

  Page<Memo> findByMnoBetween(Long mno1, Long mno2, Pageable pageable);

  // memo의 총 갯수를 로깅하게끔

  long count();

  //Mno가 특정 long , memoText가 특정 문자열일 떄의 queryMethod

  List<Memo> findByMnoOrMemoText(Long mno, String memoText);

  @Query("select m from Memo m order by m.mno desc limit 10")
  List<Memo> getListDesc();

  @Query(value = "select * from tbl_memo order by mno desc limit 10", nativeQuery = true)
  List<Memo> getListDesc2();

  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :memoText where m.mno = :mno")
  int updateMemoText(@Param("mno") Long mno,  @Param("memoText") String memoText);

  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = :#{#memo.memoText} where m.mno = :#{#memo.mno}")
  int updateMemoText2(@Param("memo") Memo memo);

  @Transactional
  @Modifying
  @Query("update Memo m set m.memoText = ?2 where m.mno = ?1")
  int updateMemoText3(Long mno, String memoText);

  @Query(value = "select m.mno, m.memoText, CURRENT_DATE from Memo m where m.mno > :mno " , countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<Object[]> getListWithQueryObject(Long mno, Pageable pageable);

  @Query(value = "select m.mno AS mno, m.memoText AS memoText, CURRENT_DATE AS n from Memo m where m.mno > :mno " , countQuery = "select count(m) from Memo m where m.mno > :mno")
  Page<MemoDTO> getListWithQueryProjection(Long mno, Pageable pageable);
}
