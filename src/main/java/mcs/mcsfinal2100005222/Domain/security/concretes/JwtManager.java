package mcs.mcsfinal2100005222.Domain.security.concretes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class JwtManager {

    @Value("${jwt-key}")
    private String SECRET;

    @Value("${jwt-expire-minute-limitation}")
    private int minuteToExpire;

    public String generateToken(String userName) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userName);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUser(token);
        Date expiration = extractExpiration(token);
        return userDetails.getUsername().equals(userName) && !expiration.before(new Date());
    }

    public Date extractExpiration(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token) // Correct method for parsing signed JWTs
                .getBody();
        return claims.getExpiration();
    }

    public String extractUser(String token) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token) // Correct method for parsing signed JWTs
                    .getBody();
            return claims.getSubject();
    }

    private String createToken(Map<String, Object> claims, String userName) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30)) // Correct expiration time calculation
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
