package alone.board.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class MysqlMemberRepository implements MemberRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlMemberRepository(DataSource dataSource) {       // DB 커넥션 정보를 알아서 주입받음
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean existsMember() {


    }
}
