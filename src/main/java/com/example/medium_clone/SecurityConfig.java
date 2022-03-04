package com.example.medium_clone;

import com.example.medium_clone.web.auth.CustomLoginFailureHandler;
import com.example.medium_clone.web.auth.CustomLoginSuccessHandler;
import com.example.medium_clone.web.auth.CustomLogoutHandler;
import com.example.medium_clone.web.auth.CustomUsernamePasswordAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAt(getAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/users/register").permitAll()
                .anyRequest().authenticated()
                .and()
                    .formLogin().disable()
                .logout()
                    .logoutUrl("/api/logout").logoutSuccessHandler(logoutSuccessHandler());
    }

    protected CustomUsernamePasswordAuthenticationFilter getAuthFilter() throws Exception{
        CustomUsernamePasswordAuthenticationFilter filter = new CustomUsernamePasswordAuthenticationFilter(objectMapper());
        filter.setFilterProcessesUrl("/api/login");
        filter.setAuthenticationManager(authenticationManager());
        filter.setUsernameParameter("email");
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(authenticationFailureHandler());
        return filter;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() { return new CustomLoginSuccessHandler(); }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() { return new CustomLoginFailureHandler(); }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { return new CustomLogoutHandler(); }

    @Bean
    public ObjectMapper objectMapper() { return new ObjectMapper(); }
}
