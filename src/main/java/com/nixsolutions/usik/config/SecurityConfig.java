package com.nixsolutions.usik.config;

import com.nixsolutions.usik.constants.RoleConstants;
import com.nixsolutions.usik.constants.UrlConstants;
import com.nixsolutions.usik.service.ClientDetailsService;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    @NonNull
    ClientDetailsService clientDetailsService;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(UrlConstants.ANGULAR_FRONT)
                .allowedMethods("*");
    }

    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(clientDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                //==============GET for All
                .antMatchers(HttpMethod.GET, UrlConstants.CLIENT, UrlConstants.PHOTO, UrlConstants.PRODUCT, UrlConstants.PRODUCT_TYPE, UrlConstants.REVIEW_PRODUCT, UrlConstants.SHOP)
                .permitAll()
                //==============GET only for AUTH
                .antMatchers(HttpMethod.GET, UrlConstants.PURCHASE, UrlConstants.ROLE, UrlConstants.STATUS)
                .authenticated()
                //==============for /client /photo
                .antMatchers(HttpMethod.POST, UrlConstants.CLIENT)
                .hasAnyRole(RoleConstants.ANONYMOUS, RoleConstants.ADMIN)
                .antMatchers(HttpMethod.PUT, UrlConstants.CLIENT, UrlConstants.PHOTO)
                .authenticated()
                .antMatchers(HttpMethod.DELETE, UrlConstants.CLIENT, UrlConstants.PHOTO)
                .authenticated()
                //==============for /photo
                .antMatchers(HttpMethod.POST, UrlConstants.PHOTO)
                .authenticated()
                //==============for /product
                .antMatchers(HttpMethod.POST, UrlConstants.PRODUCT)
                .hasRole(RoleConstants.SELLER)
                .antMatchers(HttpMethod.PUT, UrlConstants.PRODUCT)
                .hasAnyRole(RoleConstants.SELLER, RoleConstants.CUSTOMER)
                .antMatchers(HttpMethod.DELETE, UrlConstants.PRODUCT)
                .hasAnyRole(RoleConstants.SELLER, RoleConstants.ADMIN)
                //==============only for ADMIN 
                .antMatchers(HttpMethod.POST, UrlConstants.PRODUCT_TYPE, UrlConstants.ROLE, UrlConstants.STATUS)
                .hasRole(RoleConstants.ADMIN)
                .antMatchers(HttpMethod.PUT, UrlConstants.PRODUCT_TYPE, UrlConstants.PRODUCT_TYPE, UrlConstants.ROLE, UrlConstants.STATUS)
                .hasRole(RoleConstants.ADMIN)
                .antMatchers(HttpMethod.DELETE, UrlConstants.PRODUCT_TYPE, UrlConstants.PRODUCT_TYPE, UrlConstants.ROLE, UrlConstants.STATUS, UrlConstants.PURCHASE, UrlConstants.REVIEW_PRODUCT)
                .hasRole(RoleConstants.ADMIN)
                //==============for /purchase
                .antMatchers(HttpMethod.POST, UrlConstants.PURCHASE)
                .hasRole(RoleConstants.CUSTOMER)
                .antMatchers(HttpMethod.PUT, UrlConstants.PURCHASE)
                .authenticated()
                //==============for /review_product
                .antMatchers(HttpMethod.POST, UrlConstants.REVIEW_PRODUCT)
                .hasAnyRole(RoleConstants.CUSTOMER, RoleConstants.SELLER)
                .antMatchers(HttpMethod.PUT, UrlConstants.REVIEW_PRODUCT)
                .hasRole(RoleConstants.CUSTOMER)
                //==============for /shop
                .antMatchers(HttpMethod.POST, UrlConstants.SHOP)
                .hasRole(RoleConstants.SELLER)
                .antMatchers(HttpMethod.PUT, UrlConstants.SHOP)
                .hasRole(RoleConstants.SELLER)
                .antMatchers(HttpMethod.DELETE, UrlConstants.SHOP)
                .hasAnyRole(RoleConstants.SELLER, RoleConstants.ADMIN)
                //==============Access Denied Handler
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler())
                .and()
                .httpBasic()
                //==============login form
                .and()
                .formLogin()
                .loginProcessingUrl(UrlConstants.LOGIN)
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
                //==============logout
                .and()
                .logout();
    }

//    private AuthenticationSuccessHandler successHandler() {
//        return (request, response, authentication) -> {
//            setCorsInHeader(response);
//            response.getWriter().append("OK");
//            response.setStatus(200);
//        };
//    }
//
//    private AuthenticationFailureHandler failureHandler() {
//        return (request, response, e) -> {
//            setCorsInHeader(response);
//            response.getWriter().append("Authentication failure");
//            response.setStatus(401);
//        };
//    }

    private AccessDeniedHandler accessDeniedHandler() {
        return (request, response, e) -> {
            setCorsInHeader(response);
            response.getWriter().append("Access denied");
            response.setStatus(403);
        };
    }

    private void setCorsInHeader(HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
