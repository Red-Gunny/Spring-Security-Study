package alone.board.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/validation")
public class validationTestController  {

/**
    @RequestMapping("/Test")
    public String checkValidation(@RequestParam("Authentication") String jwtToken) {

        try{
            Jwts.parserBuilder()
                    .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .build()
                    .parseClaimsJws(jwtToken);
            return "validationResult";
        } catch(JwtException e) {
            return "sample";
        }

    }**/
}
