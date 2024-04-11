package com.springboot.causeconnect.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;

import com.spring.ecommerce.services.JWTService;
import com.spring.ecommerce.services.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@CrossOrigin("*")
public class JWTAuthenticationFilter extends OncePerRequestFilter{
    @Autowired
    private JWTService jwtService;
    
    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                final String authHeader = request.getHeader("Authorization");
                final String requestJwt;
                final String userEmail;

                if(StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }

                requestJwt = authHeader.substring(7);
                userEmail = jwtService.extractUsername(requestJwt);

                if(!StringUtils.isEmpty(userEmail) && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails = userService.userDetailsService().loadUserByUsername(userEmail);

                    if(jwtService.isTokenValid(requestJwt, userDetails)){

                        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                        securityContext.setAuthentication(token);
                        SecurityContextHolder.setContext(securityContext);

                    }
                }
                filterChain.doFilter(request, response);
    }

}
