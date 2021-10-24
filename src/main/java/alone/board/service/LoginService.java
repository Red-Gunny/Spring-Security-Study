package alone.board.service;

import alone.board.entity.User;
import alone.board.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;        // @RequiredArgs~ 에 의해 DI

    /**
     * DB에서 로그인 ID를 바탕으로 한 user 객체를 꺼내온다.
     * 이때 파라미터로 전달된 비밀번호와 DB에서 가져온 비밀번호가 같으면 해당 객체를 반환하고
     * 아니면 null을 반환한다.
     * 이때 만약 아예 DB에 존재하지 않는다면 이경우도 null을 반환한다.
     */
    public User login(String userId, String password) {
        Optional<User> userOptional = memberRepository.findById(userId);
        User user = userOptional.get();
        if (user.getPassword().equals(password)) {
            return user;
        } else {
            return null;
        }
    }

}
