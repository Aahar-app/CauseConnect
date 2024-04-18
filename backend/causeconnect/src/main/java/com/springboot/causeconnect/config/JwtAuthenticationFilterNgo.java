package com.springboot.causeconnect.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.StringUtils;

import com.springboot.causeconnect.ngo.services.NgoService;
import com.springboot.causeconnect.services.JWTService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
@CrossOrigin("*")
public class JwtAuthenticationFilterNgo extends OncePerRequestFilter{

            @Autowired
            private JWTService jwtService;
    
            @Autowired
            private NgoService ngoService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


                final String authHeader = request.getHeader("Authorization");
                final String requestJwt;
                final String ngoEmail;

                if(StringUtils.isEmpty(authHeader) || !authHeader.startsWith("Bearer ")){
                    filterChain.doFilter(request, response);
                    return;
                }

                requestJwt = authHeader.substring(7);
                ngoEmail = jwtService.extractUsername(requestJwt);

                if(!StringUtils.isEmpty(ngoEmail) && SecurityContextHolder.getContext().getAuthentication()==null){
                    UserDetails userDetails = ngoService.ngoDetailsService().loadUserByUsername(ngoEmail);

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
