package alone.board.controller;

import alone.board.dto.LoginForm;
import alone.board.entity.User;
import alone.board.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;        // 롬복 @Required에 의해 생성자 자동 생성

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginForm loginForm) {

        User user = loginService.login(loginForm.getUserId(), loginForm.getPassword());
        if(user == null) {
            return (ResponseEntity<User>) ResponseEntity.badRequest();
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
