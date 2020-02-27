package com.easy.filter.jwt;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.easy.auth.AuthUser;
import com.easy.data.entity.User;
import com.easy.data.entity.UserRole;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {
	private static final String secret = "malcolm@gmail.com";
	
    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", user.getRoles().parallelStream().map(UserRole::getRole).collect(Collectors.joining(",")));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getName())
                //创建时间
                .setIssuedAt(new Date())
                //过期时间，我们设置为 50分钟
                .setExpiration(new Date(System.currentTimeMillis() + 50 * 60 * 1000))
                //签名，通过密钥保证安全性
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public static AuthUser parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        String username = claims.getSubject();
        String roles = (String) claims.get("roles");
        
        return new AuthUser(username, null, Arrays.stream(roles.split(",")).map(name -> {
        	UserRole role = new UserRole();
            role.setRole(name);
            return role;
        }).collect(Collectors.toList()));
    }
}
