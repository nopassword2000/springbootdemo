package com.mybatis.config;

import com.mybatis.filter.PageFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;

@Configuration
public class config {


    @Bean
    FilterRegistrationBean filterRegistrationBean(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new PageFilter());
        ArrayList arrayList = new ArrayList();
        arrayList.add("/*");
        registrationBean.setUrlPatterns(arrayList);
        return registrationBean;
    }

}
