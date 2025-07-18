package com.climbjava.spring_basic.config;

import com.climbjava.spring_basic.aop.BoardService;
import com.climbjava.spring_basic.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class DataSourceTest {
  @Autowired
  private DataSource dataSource;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  public void test() {
    log.info("{}", dataSource);
    log.info("{}", jdbcTemplate);
  }

  @Test
  public void testGetMembers() {
    jdbcTemplate.queryForList("select * from tbl_member").forEach(System.out :: println);
  }

  @Test
  public void testIter(){
    List<Integer> list = List.of(2, 3, 4, 1);
    Iterator<Integer> iterator = list.iterator();

    while (iterator.hasNext()){
      System.out.println(iterator.next());
    }
  }

  @Test
  public void testCursor() throws SQLException {
    Connection conn = jdbcTemplate.getDataSource().getConnection();
    PreparedStatement ps = conn.prepareStatement("select * from tbl_member where id = ?");
    ps.setString(1, "sae");
    ResultSet rs = ps.executeQuery();
    while (rs.next()){
//      log.info(rs.getCursorName());
      int no = rs.getInt(1);
      String name = rs.getString(4);
      log.info("{} {}", no, name);
    }
  }

  @Test
  public void testCallFunction(){
   int a = jdbcTemplate.queryForObject("select add_num(?, ?)", int.class, 10, 20);
    log.info("{}", a);
  }

  @Test
  public void testCallProcedure(){
    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate);
    call.withProcedureName("list_members");

    Map<String, Object> map = call.execute();
    log.info("{}", map);
  }

  @Test
  public void testCallProcedure2(){
    List<Member> members = jdbcTemplate.query("call list_members()", new RowMapper<Member>() {
      @Override
      public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Member.builder().id(rs.getString("id")).name(rs.getString("name")).build();
      }
    });
    log.info("{}", members);
  }

  @Test
  public void testCallProcedure3(){
    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withCatalogName("pbl").withProcedureName("find_member_by_id").declareParameters(new SqlParameter("v_id", Types.VARCHAR));

    Map<String, Object> map = call.execute("abcd");
    log.info("{}", map);
  }
  @Test
  public void testCallProcedure4(){
    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withCatalogName("pbl")
            .withProcedureName("find_name_by_id")
            .declareParameters(new SqlParameter("v_name", Types.VARCHAR), new SqlOutParameter("m_name", Types.VARCHAR));

    Map<String, Object> map = call.execute("abcd");
    log.info("{}", map);
    log.info("{}", map.get("m_name"));
  }

  @Test
  public void testCallProcedure5(){
    SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate).withCatalogName("pbl")
            .withProcedureName("add_five")
            .declareParameters(new SqlInOutParameter("num", Types.INTEGER));
    Map<String, Object> map = call.execute(10);
    log.info("{}", map);
  }
}
