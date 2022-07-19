package com.alkemy.disneyworldspringbootapplication.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${disney.app.security.jwtSecret}")
    private String jwtSecret;

    @Value("${disney.app.security.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public String generateJwtToken(Authentication authentication) {

        User userPrincipal = (User) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException expiredJwtException){
            logger.error("Expired jwt: {}", expiredJwtException.getMessage());
        } catch (UnsupportedJwtException unsupportedJwtException) {
            logger.error("unsupported jwt: {}", unsupportedJwtException.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            logger.error("malformed jwt: {}", malformedJwtException.getMessage());
        } catch (SignatureException signatureException) {
            logger.error("invalid signature jwt: {}", signatureException.getMessage());
        } catch (IllegalArgumentException argumentException) {
            logger.error("invalid argument: {}", argumentException.getMessage());
        }
        return false;
    }
}
