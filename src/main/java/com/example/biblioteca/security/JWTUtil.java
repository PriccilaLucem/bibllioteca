package com.example.biblioteca.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

import org.hibernate.cfg.Environment;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.biblioteca.models.AdmUserModel;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class JWTUtil {


    private static String SECRET_KEY = Environment.getProperties().getProperty("SECRET_KEY");
    private String token;

    public JWTUtil(){}

    public JWTUtil(String token){
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    
    private static SecretKey generateKey() {
        byte[] secretKeyBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        return new SecretKeySpec(secretKeyBytes, SignatureAlgorithm.HS256.getJcaName());
    }

    public static String generateToken(AdmUserModel user) {
        SecretKey key = generateKey();
        String token = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setId(user.getId())
                .claim("email", user.getEmail())
                .claim("is_adm", user.getIsAdm())
                .compact();

        return token;
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.split(" ")[1];
        }
        return null;
    }

    public boolean validateToken(String jwtToken) {
        SecretKey key = generateKey();
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parse(jwtToken);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // public Authentication getAuthentication(String token) {
    //     String userId = parseUserIdFromToken(token);
    //     List<String> roles = parseRolesFromToken(token);
    //     List<SimpleGrantedAuthority> authorities = createAuthorities(roles);
    //     return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    // }

    public static String parseUserIdFromToken(String token) {
        byte[] secretKeyBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getId();
    }

    private List<String> parseRolesFromToken(String token) {
        byte[] secretKeyBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes());
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<String> roles = new ArrayList<>();
        Object obj = claims.getOrDefault("is valid", "false");
        if (obj.equals(true)) {
            roles.add("ROLE_USER_IS_VALID");
        } else {
            roles.add("ROLE_USER_IS_NOT_VALID");
        }
        return roles;
    }

    // private List<SimpleGrantedAuthority> createAuthorities(List<String> roles) {
    //     List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    //     for (String role : roles) {
    //         authorities.add(new SimpleGrantedAuthority(role));
    //     }
    //     return authorities;
    // }

    public Claims parseTokenToObject(String token) {
        byte[] secretKeyBytes = Base64.getEncoder().encode(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKeyBytes))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
