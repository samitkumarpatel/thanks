package api.thanks.jwtsecurity.utility;

import api.thanks.jwtsecurity.model.Member;
import api.thanks.jwtsecurity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTGenerator {

    public String generateToken(User u, Member m, String secret) {
        m.setPassword(null);
        Claims claims = Jwts.claims().setSubject(u.getUsername());
        claims.put("userId", u.getUsername());
        claims.put("member",m);
        claims.put("role", "NA");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .setExpiration(new Date(System.currentTimeMillis()+(60*60*1000))) //will expire in next one hr
                .compact();
    }

}
