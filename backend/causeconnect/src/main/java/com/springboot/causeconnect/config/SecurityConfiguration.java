package com.springboot.causeconnect.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;


import com.springboot.causeconnect.user.services.UserService;
import com.springboot.causeconnect.entities.Role;
import com.springboot.causeconnect.ngo.services.NgoService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@CrossOrigin("*")
public class SecurityConfiguration {

    @Autowired
    private JWTAuthenticationFilterUser jwtAuthenticationFilterUser;

    @Autowired
    private JwtAuthenticationFilterNgo jwtAuthenticationFilterNgo;

    @Autowired
    private UserService userService;

    @Autowired
    private NgoService ngoService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> request.requestMatchers("/api/auth/**")
        .permitAll()
        .requestMatchers("/api/donationrequest/user/**")
        .permitAll()
        .requestMatchers("/api/ngo").hasAnyAuthority(Role.NGO.name())
        .requestMatchers("/api/user").hasAnyAuthority(Role.USER.name())
        .anyRequest().authenticated()
        
        )

        .sessionManagement(manager-> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
       // .authenticationProvider(authenticationProviderNgo()).addFilterBefore(jwtAuthenticationFilterNgo, UsernamePasswordAuthenticationFilter.class);
         .authenticationProvider(authenticationProviderUser()).addFilterBefore(jwtAuthenticationFilterUser, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();


        
        

        
    }

    @Bean
    public AuthenticationProvider authenticationProviderUser(){
        DaoAuthenticationProvider authenticationProviderUser = new DaoAuthenticationProvider();
        authenticationProviderUser.setUserDetailsService(userService.userDetailsService());
        authenticationProviderUser.setPasswordEncoder(passwordEncoder());
        
        return authenticationProviderUser;
    }

    // @Bean
    // public AuthenticationProvider authenticationProviderNgo(){
    //     DaoAuthenticationProvider authenticationProviderNgo = new DaoAuthenticationProvider();
    //     authenticationProviderNgo.setUserDetailsService(ngoService.ngoDetailsService());
    //     authenticationProviderNgo.setPasswordEncoder(passwordEncoder());
        
    //     return authenticationProviderNgo();
    // }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();

    }
}
