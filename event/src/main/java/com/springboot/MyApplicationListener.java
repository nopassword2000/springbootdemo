package com.springboot;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.ResolvableType;

public class MyApplicationListener implements GenericApplicationListener {


    @Override
    public boolean supportsEventType(ResolvableType eventType) {

        return true;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {

        System.out.println("evnetName===" + event.getClass().getName());
    }
}
