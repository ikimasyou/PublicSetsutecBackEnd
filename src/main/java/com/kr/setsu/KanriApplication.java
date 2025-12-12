package com.kr.setsu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan(basePackages = {"com.kr.setsu.mapper","com.kt.setsu"})
//@ServletComponentScan	//该注解用于扫描RSAFilter，RSAFilter用于HTTP请求的加密传输。
public class KanriApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(KanriApplication.class, args);
//        String [] allBeansName = ac.getBeanDefinitionNames();
//        for(String bean:allBeansName) {
//        	System.out.println(bean);
//        }
        
    }

}
