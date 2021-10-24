package alone.board.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@NoArgsConstructor
@RequestMapping("/jwt")
@Controller
public class JwtController extends HttpServlet {


    @Override
    @RequestMapping("/generate")
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setStatus(HttpServletResponse.SC_OK);

        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Authentication", makeJwt());
        response.setHeader("Location", "/sample.html");

    }

    @RequestMapping("/generate")
    public String makeJwt() {

        String jwtToken = Jwts
                .builder()
                .setSubject("Hong")
                .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .compact();

        return jwtToken;
    }
}
