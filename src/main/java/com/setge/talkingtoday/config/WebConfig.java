package com.setge.talkingtoday.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home"); // 홈
        registry.addViewController("/customlogin").setViewName("customlogin"); // 로그인폼
//        registry.addViewController("/chatting").setViewName("chatting"); // 채팅

    }
}
