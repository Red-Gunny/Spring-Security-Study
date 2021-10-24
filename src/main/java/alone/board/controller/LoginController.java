package alone.board.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @PostMapping("/login")
    public String login(@RequestParam("ID") String userId, @RequestParam("Password") String password) {




    }
}
