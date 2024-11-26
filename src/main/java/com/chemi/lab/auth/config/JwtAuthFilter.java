package com.chemi.lab.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull  HttpServletResponse response,
                                    @NonNull  FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
//        System.out.println(authHeader + " kkkkkkkkkkkkkkk");
        if (authHeader == null || !authHeader.trim().startsWith("Bearer ")){
            filterChain.doFilter(request,response); // go to the next filter
            return;
        }
        jwt = authHeader.substring(7);//extract token
        if (jwt.equals("null")){
            filterChain.doFilter(request,response); // go to the next filter
            return;
        }

        final String userEmail;
        userEmail = jwtService.extractUsername(jwt);//extract user email from token

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails customer = this.userDetailsService.loadUserByUsername(userEmail); //get data from db
            if (jwtService.isTokenValid(jwt,customer)){ //update security content
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        customer,
                        null,
                        customer.getAuthorities()
                );
                authenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
