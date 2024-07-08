package com.neusoft.ysu.nepmserve.util;

import com.neusoft.ysu.nepmserve.util.LoginHandlerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyInterceptorConfig implements WebMvcConfigurer {

        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

    }
//
//    static final String IMG_PATH=System.getProperty("user.dir")+"/static/";
//
////    @Value("${prop.upload-folder}")
////    private String UPLOAD_FOLDER;
//
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new LoginHandlerInterceptor()).excludePathPatterns("/login").excludePathPatterns("/static/**").excludePathPatterns("/img/**");
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//
//        registry.addResourceHandler("/static/**").addResourceLocations("file:"+IMG_PATH);
////        registry.addResourceHandler("/img/**").addResourceLocations("file:"+UPLOAD_FOLDER);
//    }
}
