package com.springboot;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.AbstractApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

@Component("applicationEventMulticaster")
public class MyApplicationEventMulticaster extends SimpleApplicationEventMulticaster {


    public MyApplicationEventMulticaster(){

        Executor executor = ForkJoinPool.commonPool();
        setTaskExecutor(executor);
    }
}
