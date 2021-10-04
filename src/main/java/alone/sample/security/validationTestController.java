package alone.sample.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;

@Controller
@RequestMapping("/validation")
public class validationTestController  {


    @RequestMapping("/Test")
    public String checkValidation(@RequestParam("Authentication") String jwtToken) {







        Jws<Claims> jws;

        try{
            Jwts.parserBuilder()
                    .setSigningKey(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                    .build()
                    .parseClaimsJws(jwtToken);
            return "validationResult";
        } catch(JwtException e) {
            return "sample";
        }

    }
}
