import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class CreatedJwt {
    public static void main(String[] args) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("66")
                .setSubject("easy")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "asdf")
                .claim("companyId", "1234")
                .claim("companyName", "ax");
        String token = jwtBuilder.compact();
    }
}
