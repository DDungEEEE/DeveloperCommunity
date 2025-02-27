package io.devcommunity.developer_community.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Slf4j(topic = "Jwt CRUD Service Class")
public class JwtUtil {

    @Value("${ACCESS_TOKEN_EXPIRATION_TIME}")
    private int ACCESS_TOKEN_EXPIRED;

    @Value("${REFRESH_TOKEN_EXPIRATION_TIME}")
    private int REFRESH_TOKEN_EXPIRED;

    @Value("${JWT_SECRET_KEY}")
    private String jwtSecretKey;

    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @PostConstruct
    public void init(){
        byte[] decode = Base64.getDecoder().decode(jwtSecretKey);
        key = Keys.hmacShaKeyFor(decode);
    }

    public boolean validToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        }catch (SecurityException | MalformedJwtException e){
            log.error("Invalid Jwt Signature, 유효허지 않은 Jwt 서명입니다.");
        }catch (ExpiredJwtException e){
            log.error("유효기간이 만료된 Jwt Token 입니다.");
        }catch (UnsupportedJwtException e){
            log.error("지원하지 않는 Jwt Token 입니다.");
        }catch (IllegalArgumentException e){
            log.error("{}", e.getMessage());
        }
        return false;
    }

    public String generateAccessToken(String userName){
        Date date = new Date();

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(date.getTime() + ACCESS_TOKEN_EXPIRED))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public String generateRefreshToken(String userName){
        Date date = new Date();

        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(date.getTime() + REFRESH_TOKEN_EXPIRED))
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public String getJwtToken(HttpServletRequest req){
        String authorizationHeader = "Authorization";

        String bearerToken = req.getHeader(authorizationHeader);
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    protected Claims getClaims(String token){
        try{
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }catch (ExpiredJwtException ex){
            log.error("{}", ex.getMessage());
            return null;
        }
    }
}
