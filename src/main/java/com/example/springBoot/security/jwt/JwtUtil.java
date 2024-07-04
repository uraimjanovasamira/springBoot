package com.example.springBoot.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {

    @Value("${secret}")
    private String secret;

    private final Long TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000L; //1week

    //бул методтордун баардыгы токенди утверждение кылыш учун керек

    //создание токена
    public String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)//утверждение
                .setSubject(subject)//токендин темасын берип атабыз
                .setIssuedAt(new Date(System.currentTimeMillis()))//токен иштей турган убакытты берип атабыз
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))//токендин буткон убактысын берип атабыз
                .signWith(SignatureAlgorithm.HS256, secret)//создаем алгоритм
                .compact();
    }

    //создание токена
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }


    //метод нужен для утверждении(текшеруу учун),токен келет ошол токенден пользовательдин данныйларын алганга жардам берет
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> function) {
        final Claims claims = getAllClaimsFromToken(token);
        return function.apply(claims);
    }

    //токендин буто турган датасын чыгарат
    public Date getExpirationDateToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateToken(token);
        return expiration.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token, Claims::getSubject);
    }

    public Boolean tokenIsValidate(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


}

