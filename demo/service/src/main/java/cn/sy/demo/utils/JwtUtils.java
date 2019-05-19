package cn.sy.demo.utils;

import cn.sy.demo.constant.role.JwtConstant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtils {

    public static String generateToken(UserDetails userDetails) {
        Claims claims = new DefaultClaims();
        claims.setSubject(userDetails.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstant.EXPIRATION_TIME * 1000))
                .signWith(SignatureAlgorithm.HS512, JwtConstant.SECRET )
                .compact();
    }


}
