package com.goktan.springsecurityexample.security;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private  final String SECRET_KEY = "myAppSecretValue123";

    private final long EXPIRES_IN =  604800;


    public String generateJwtToken(Authentication authentication){// this method will generate tokens
        JwtUserDetails jwtUserDetails = (JwtUserDetails) authentication.getPrincipal(); // I got information from authentication param then I do cast information from auth to jwtuserdetails
        Date date = new Date(new Date().getTime()+EXPIRES_IN);

        return Jwts.builder().setSubject(Long.toString(jwtUserDetails.getId())).setIssuedAt(new Date()).setExpiration(date).signWith(SignatureAlgorithm.HS512,SECRET_KEY).compact(); //made token .
    }


    public Long getUserIdFromJwtToken(String jwtToken){ // We got to userId from token  with this method.
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String jwtToken){ // ıt do check to value of token.
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken);
            return !isTokenProvided(jwtToken); // token not validate  if ıt will send to false
        }catch (SignatureException e){
            return false;
        }catch (MalformedJwtException e){
            return false;
        }catch (ExpiredJwtException e){
            return false;
        }catch (UnsupportedJwtException e){
            return false;
        }catch (IllegalArgumentException e){
            return false;
        }

    }


    public boolean isTokenProvided(String jwtToken){
        Date expiration  = (Date) Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
        return expiration.before(new Date());  // this method will turn true , if expiration date before from newDate . its expired.
    }
}
