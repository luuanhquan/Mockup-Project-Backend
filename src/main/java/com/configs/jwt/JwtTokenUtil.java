package com.configs.jwt;

import com.entity.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    @Value("${jwt.secret}")
    private String secret;
    public static final long JWT_TOKEN_VALIDITY = 5*60*60;

    public String getUsernameFromToken(String token){
        return getClaimsFromToken(token,Claims::getSubject);
    }

    public Date getIssuedAtDateFromToken(String token){
        return getClaimsFromToken(token, Claims::getIssuedAt);
    }

    private <T> T getClaimsFromToken(String token, Function<Claims, T> claimResolver){
        final Claims claims= getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }


    private Boolean isTokenEpired(String token){
        final Date expriration = getExprirationDateFromToken(token);
        return expriration.before(new Date());
    }

    private Date getExprirationDateFromToken(String token) {
        return getClaimsFromToken(token, Claims::getExpiration);
    }

    private Boolean ignoreTokenExpiration(String token){
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    public String generateToken(CustomUserDetails userDetails){
        Map<String, Object> claims= new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    public Boolean validateToken(String token, CustomUserDetails userDetails){
        final String username= getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername())&& !isTokenEpired(token));
    }
}
