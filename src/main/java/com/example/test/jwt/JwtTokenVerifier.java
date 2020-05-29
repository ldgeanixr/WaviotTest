package com.example.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenVerifier extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader("Authorization");

        if( (authorizationHeader == null || authorizationHeader.isEmpty()) || !authorizationHeader.startsWith("Bearer ")){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            String token = authorizationHeader.replace("Bearer ", "");
            String key = "securesecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecuresecure";
            Jws<Claims> claimsJws =  Jwts.parser()
                    .setSigningKey(Keys.hmacShaKeyFor(key.getBytes()))
                    .parseClaimsJws(token);

            Claims body = claimsJws.getBody();
            String username = body.getSubject();

            System.out.println("HAAAAAA 1");

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, null);

            System.out.println("HAAAAAA 2");


            SecurityContextHolder.getContext().setAuthentication(authentication);

            System.out.println("HAAAAAA 3");


        } catch (JwtException ex){
            throw new IllegalStateException("Token cannot be trusted");
        }

        filterChain.doFilter(request, response);

    }
}
