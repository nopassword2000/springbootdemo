package com.springboot;


import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;


@Component
public class MyEventListener implements ApplicationListener<MyEvent> {


    @Override
    public void onApplicationEvent(MyEvent event) {

        System.out.println("this is event:" + event.getMessage());
    }
}
