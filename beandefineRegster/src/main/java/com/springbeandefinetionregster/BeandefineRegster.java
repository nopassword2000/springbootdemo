package com.springbeandefinetionregster;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;

@SpringBootApplication
@Import(GenBean.class)
public class BeandefineRegster implements CommandLineRunner {

    @Autowired
    ApplicationContext applicationContext;


    @Autowired
    BeanDefineUser user;

    public static void main(String[] args) {
        SpringApplication.run(BeandefineRegster.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(user.age);
    }
}
