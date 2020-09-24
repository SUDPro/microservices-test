package ru.itis.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    public Long getUserIdFromJWT(String badToken) {
        String token = getOnlyToken(badToken);
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    private String getOnlyToken(String badToken) {
        return badToken.split(" ")[1];
    }
}
