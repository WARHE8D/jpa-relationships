package com.warhe8d.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtConfig {
    private static final String SIGNING_KEY = "5558fbbba28bc468507aa45cbf2d74d286cf043ff4ccee9f531b5d8bc5586052";
    public String getUsername(String jwt) {
        return extractClaim(jwt,Claims::getSubject);
    }

    private <T> T extractClaim(String jwt, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(jwt);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String jwt) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(SIGNING_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(Map<String,Object> extractClaims, UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 *60))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    //to generate token without using claim hashmap
    public String generateToken(UserDetails ud){
        return generateToken(new HashMap<>(),ud);
    }

    public boolean isTokenValid(String jwt,UserDetails ud){
        final String userName = getUsername(jwt);
        return (userName.equals(ud.getUsername())) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return getTokenExpiry(jwt).before(new Date());
    }

    private Date getTokenExpiry(String jwt) {
        return extractClaim(jwt,Claims::getExpiration);
    }
}
