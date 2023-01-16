package com.example.controleservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

public class CorsConfig {

    private static final List<String> PERMIT_ALL = List.of("*");
    private static final List<String> HTTP_METHODS = List.of("POST", "GET", "PUT", "DELETE", "PATCH");

//    @Bean
//    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
//        var corsConfig = new CorsConfiguration();
//        corsConfig.setAllowedOrigins(PERMIT_ALL);
//        corsConfig.setAllowedMethods(HTTP_METHODS);
//        corsConfig.setAllowedHeaders(PERMIT_ALL);
//
//        var source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", corsConfig);
//
//        var bean = new FilterRegistrationBean<CorsFilter>();
//        bean.setFilter(new CorsFilter(source));
//        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//
//        return bean;
//    }
}
