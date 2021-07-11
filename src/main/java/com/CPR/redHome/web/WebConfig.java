package com.CPR.redHome.web;

import com.CPR.redHome.web.argumentresolver.LoginMemberArgumentResolver;
import com.CPR.redHome.web.interceptor.LogInterceptor;
import com.CPR.redHome.web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        //resolver 목록에 생성한 LoginMemberArgumentResolver 추가
        resolvers.add(new LoginMemberArgumentResolver());

    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/error");

//        registry.addInterceptor(new LoginCheckInterceptor())
//                .order(2)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/","/login","/logout","/join",
//                        "/css/**/**.css","js/**","/img/**/**","/error");

    }




}
