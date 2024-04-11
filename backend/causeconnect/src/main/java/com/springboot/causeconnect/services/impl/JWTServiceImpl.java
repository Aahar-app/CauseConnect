package com.springboot.causeconnect.services.impl;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import java.security.Key;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import com.springboot.causeconnect.services.JWTService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService{

    public String generateToken(UserDetails userDetails){

        return Jwts.builder().setSubject(userDetails.getUsername())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    public String generateRefreshToken(Map<String,Object> extraClaims, UserDetails userDetails){

        return Jwts.builder().setClaims(extraClaims).setSubject(userDetails.getUsername())
                            .setIssuedAt(new Date(System.currentTimeMillis()))
                            .setExpiration(new Date(System.currentTimeMillis() + 604800000))
                            .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

    }

    //this method will extract username from token
    private <T> T extractClaim(String token, Function <Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);

    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token){

        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }



    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode("4133B309843405864232345456465FDGJJ34389590583409FGE2332343u4894389");
        return Keys.hmacShaKeyFor(key);
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token){
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    
}
