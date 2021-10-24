package alone.board.repository;

import alone.board.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Optional;

@Repository
public class MysqlMemberRepository implements MemberRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MysqlMemberRepository(DataSource dataSource) {       // DB 커넥션 정보를 알아서 주입받음
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Optional<User> findById(String userId) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                "SELECT id, password, name FROM user WHERE id=?",
                ((rs, rowNum) -> {
                    User entity = new User(rs.getString("id"), rs.getString("password"), rs.getString("name"));
                    return entity;
                }),
                userId));
    }

}
