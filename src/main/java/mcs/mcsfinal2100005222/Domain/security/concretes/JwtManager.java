package mcs.mcsfinal2100005222.Domain.security.concretes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import mcs.mcsfinal2100005222.Infrastructure.mysql.entities.user.Abstracts.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class JwtManager {

    @Value("${jwt-key}")
    private String SECRET;

    @Value("${jwt-expire-minute-limitation}")
    private int minuteToExpire;

    public String generateToken(String userName, Set<Role> userType) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userType",userType);
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

    public Set<Role> extractUserType(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        List<String> roleNames = (List<String>) claims.get("userType");
        Set<Role> roles = roleNames.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
        return roles;
    }
    public String extractUser(String token) {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
    }

    private String createToken(Map<String, Object> claims, String userName ) {
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
