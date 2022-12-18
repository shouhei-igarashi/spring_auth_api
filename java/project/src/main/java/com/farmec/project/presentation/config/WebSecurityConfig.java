package com.farmec.project.presentation.config;

import com.farmec.project.application.service.security.impl.UserDetailsServiceImpl;
import com.farmec.project.domain.type.secure.auth.Roles;
import com.farmec.project.presentation.config.jwt.JwtTokenEndPoint;
import com.farmec.project.presentation.config.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final UserDetailsServiceImpl userDetailService;
    private final JwtTokenEndPoint jwtTokenEndPoint;
    private final JwtTokenFilter jwtTokenFilter;
    
    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailService, JwtTokenEndPoint jwtTokenEndPoint, JwtTokenFilter jwtTokenFilter)
    {
        this.userDetailService = userDetailService;
        this.jwtTokenEndPoint = jwtTokenEndPoint;
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable();

        http
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                .antMatchers("/api/home/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/admin/**").hasAnyAuthority(Roles.ROLE_ADMIN.name())
                .antMatchers(HttpMethod.POST, "/api/user/**", "/api/user/detail/**").hasAnyAuthority(Roles.ROLE_ADMIN.name(), Roles.ROLE_USER.name())
                .anyRequest()
                .authenticated();

        http
                .exceptionHandling().authenticationEntryPoint(jwtTokenEndPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}