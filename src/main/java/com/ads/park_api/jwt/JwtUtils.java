package com.ads.park_api.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import static io.jsonwebtoken.Jwts.builder;
import static io.jsonwebtoken.Jwts.parserBuilder;

@Slf4j
public class JwtUtils {

    public static final String JWT_BEARER = "Bearer ";
    public static final String JWT_AUTHORIZATION = "authorization";
    public static final String SECRET_KEY = "0123456789-0123456789-0123456789";
    public static final long EXPIRE_DAY = 0;
    public static final long EXPIRE_HOUR = 0;
    public static final long EXPIRE_MINUTE = 2;

    private JwtUtils(){
    }

    private static Key generateKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
    // metodo que gera a data de expiração do token
    private static Date tokenExpirationDate(Date start) {
        LocalDateTime dateTime = start.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime end = dateTime.plusDays(EXPIRE_DAY).plusHours(EXPIRE_HOUR).plusMinutes(EXPIRE_MINUTE);
        return Date.from(end.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    // metodo JwtToken
    public static JwtToken createToken(String username, String role) {
        Date issuedAt = new Date();
        Date limit = tokenExpirationDate(issuedAt);

        String token = builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(limit)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .claim("role", role)
                .compact();
        return new JwtToken(token);
    }
    // recupera o conteudo do token Claims
    private static Claims getClaimsFormatToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(refactorToken(token))
                    .getBody();
        } catch (JwtException e) {
            log.error(String.format("Token inválido: %s", token), e);
        }
        return null;
    }
    // recupera o username do token
    public static String getUsernameFromToken(String token) {
       return getClaimsFormatToken(token).getSubject();
    }
    // retorna um boolean se o token for valido ou não
    public static boolean isTokenValid(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(generateKey())
                    .build()
                    .parseClaimsJws(refactorToken(token));
            return true;
        } catch (JwtException e) {
            log.error(String.format("Token inválido: %s", token), e);
        }
        return false;
    }

    private static String refactorToken(String token) {
        if (token.startsWith(JWT_BEARER)) {
            return token.substring(JWT_BEARER.length());
        }
        return token;
    }

}
