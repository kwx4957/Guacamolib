package com.example.demo.global.security;

import com.example.demo.domain.user.application.CustomOAuth2UserService;
import com.example.demo.domain.user.dto.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.demo.domain.user.dto.JwtAuthenticationFilter;
import com.example.demo.domain.user.dto.JwtTokenProvider;
import com.example.demo.domain.user.dto.OAuth2AuthenticationFailureHandler;
import com.example.demo.domain.user.dto.OAuth2AuthenticationSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Slf4j
@Configuration
public class WebSecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;

    public WebSecurityConfig(CustomOAuth2UserService customOAuth2UserService,
        JwtTokenProvider jwtTokenProvider,
        OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler,
        OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.oAuth2AuthenticationSuccessHandler = oAuth2AuthenticationSuccessHandler;
        this.oAuth2AuthenticationFailureHandler = oAuth2AuthenticationFailureHandler;
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http.cors().and()
            .csrf().disable()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()

             .httpBasic().disable()
             .formLogin().disable()

            .authorizeHttpRequests().requestMatchers("/","/login/**","/user/**").permitAll()
             .and()

             .oauth2Login()
                .authorizationEndpoint().baseUri("/user/login")
                .authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
             .and()
                .redirectionEndpoint()
                .baseUri("/login/oauth2/code/**")

             .and()
                .userInfoEndpoint().userService(customOAuth2UserService)

             .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler)
             .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
         return http.build();
    }

}
