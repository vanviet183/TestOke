package com.hit.product.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/register",
            "/verifyRegistration*",
            "/resendVerifyToken*",
            "/api/v1/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()
//                .antMatchers().permitAll();
                .antMatchers(WHITE_LIST_URLS).permitAll();
        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable()
//                .authorizeRequests().antMatchers().permitAll()
//                .antMatchers(WHITE_LIST_URLS).permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//    }



//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }

}
