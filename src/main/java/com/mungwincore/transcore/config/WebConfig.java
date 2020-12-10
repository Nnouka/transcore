package com.mungwincore.transcore.config;

import com.mungwincore.transcore.interceptors.LoggerInterceptor;
import com.mungwincore.transcore.interceptors.TransInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TransInterceptor());
        registry.addInterceptor(new LoggerInterceptor());
    }
}
