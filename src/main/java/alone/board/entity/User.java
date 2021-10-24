package alone.board.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor        // final 붙은 변수의 생성자 자동생성.
public class User {
    private final String userId;
    private final String password;
    private final String userName;
}
