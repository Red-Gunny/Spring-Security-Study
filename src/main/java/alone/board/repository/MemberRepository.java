package alone.board.repository;

import alone.board.entity.User;

import java.util.Optional;

public interface MemberRepository {
    Optional<User> findById(String userId);
}
