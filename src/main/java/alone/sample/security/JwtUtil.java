package alone.sample.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private Key key;

    public JwtUtil(String secret) { // 외부에서 시크릿 키 주입
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    public String createToken(long id, String name) {
        String token = Jwts.builder()
                .claim("userId", id)        // payload에 들어갈 부분
                .claim("name", name)        //
                .signWith(key, SignatureAlgorithm.HS256)    // 고유한 키값을 해싱싱
               .compact();
        return token;
    }

    public Claims getClamins(String token) {//JWT조회(?)
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)//싸인이 포함된 jwt = jws
                .getBody();
        return claims;
    }

}
