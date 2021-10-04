package alone.sample.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor        // final 붙은 변수의 생성자 자동생성.
public class Person {
    private final String userName;
    private final String password;
}
