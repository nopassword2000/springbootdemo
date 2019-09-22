package com.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootApplication implements CommandLineRunner {

    @Autowired
    ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(SpringbootApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        MyEvent myEvent = new MyEvent(this, this.getClass().getCanonicalName());
        context.publishEvent(myEvent);


    }
}
