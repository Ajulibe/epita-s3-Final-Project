package com.ajulibe.java.SpringBootApi.security.authentication;

import com.ajulibe.java.SpringBootApi.repository.JwtUserRepo;
import com.ajulibe.java.SpringBootApi.security.authentication.helpers.AuthSuccessHandler;
import com.ajulibe.java.SpringBootApi.security.authentication.filters.JsonObjectAuthenticationFilter;
import com.ajulibe.java.SpringBootApi.security.authentication.filters.JwtAuthorizationFilter;
import com.ajulibe.java.SpringBootApi.security.authentication.helpers.AuthUnauthorizedHandler;
import com.ajulibe.java.SpringBootApi.security.authentication.helpers.CustomAuthenticationFailureHandler;
import com.ajulibe.java.SpringBootApi.service.jwt.JwtUserDetailsService;
import com.okta.spring.boot.oauth.Okta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.HeaderContentNegotiationStrategy;




/*
 * This is the main security configuration. To get this to work come sub-classes
 * were meade in the previous files
 * */


@Configuration
public class SecurityConfiguration {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUnauthorizedHandler unauthorizedHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    private final AuthSuccessHandler authSuccessHandler;
    private final JwtUserDetailsService jwtUserDetailsService;


    public SecurityConfiguration(AuthSuccessHandler authSuccessHandler, JwtUserDetailsService jwtUserDetailsService, JwtUserRepo userRepo) {
        this.authSuccessHandler = authSuccessHandler;
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeHttpRequests((auth) -> {
//                    try {
//                        auth
//                                .antMatchers("/user").hasRole("USER")
//                                .antMatchers("/admin").hasRole("ADMIN")
//                                .antMatchers(HttpMethod.POST, "/register").permitAll()
//                                .anyRequest().authenticated()
//                                .and()
//                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                                .and()
//                                .formLogin()
////                                .loginPage("/login.html")
//                                .permitAll()
//                                .failureHandler(customAuthenticationFailureHandler)
//                                .and()
//                                .logout()
//                                .permitAll()
//                                .logoutSuccessUrl("/")
//                                .and()
//                                //check the request to see if there is an auth token already present
//                                .addFilter(new JwtAuthorizationFilter(authenticationManager, jwtUserDetailsService))
//                                //generate a token for the user
//                                .addFilter(authenticationFilter())
//                                .exceptionHandling()
//                                .authenticationEntryPoint(unauthorizedHandler);
//                    } catch (Exception e) {
//                        throw new RuntimeException(e);
//                    }
//                })
//                .httpBasic(Customizer.withDefaults());
//        return http.build();
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable Cross Site Request Forgery
        http.csrf().disable();

        // Protect endpoints at /api/<type>/secure
        http.authorizeRequests(configurer ->
                        configurer
                                .antMatchers("/rate-movie").permitAll()
                                .antMatchers("/api/v1/**")
                                .authenticated())
                .oauth2ResourceServer()
                .jwt();

        // Add CORS filters
        http.cors();

        // Add content negotiation strategy
        http.setSharedObject(ContentNegotiationStrategy.class,
                new HeaderContentNegotiationStrategy());

        // Force a non-empty response body for 401's to make the response friendly
        Okta.configureResourceServer401ResponseBody(http);

        return http.build();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authSuccessHandler);
        filter.setAuthenticationManager(authenticationManager);
        return filter;
    }

}
