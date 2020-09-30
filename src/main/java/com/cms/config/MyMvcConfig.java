package com.cms.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer{
	 /*资源处理器*/
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/"+"/static/");
       registry.addResourceHandler("/common/**").addResourceLocations("/common/");
       registry.addResourceHandler("/upload/**").addResourceLocations("file:"+"C:/my/upload/");
       registry.addResourceHandler("/ueditor/**").addResourceLocations("file:"+"C:/my/ueditor/");
       registry.addResourceHandler("/lb/**").addResourceLocations("file:"+"C:/my/lb/");
    }

}
