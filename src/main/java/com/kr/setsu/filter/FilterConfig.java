package com.kr.setsu.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<RSAFilter> rsaFilter(RSAFilter rsaFilter) {
        FilterRegistrationBean<RSAFilter> registrationBean = new FilterRegistrationBean<>();
        //registrationBean.setFilter(new RSAFilter());
        registrationBean.setFilter(rsaFilter);
        registrationBean.addUrlPatterns("/attendance/shuTaiKin/*", "/user/login/*");
        // 其他配置
        return registrationBean;
    }
    
}
