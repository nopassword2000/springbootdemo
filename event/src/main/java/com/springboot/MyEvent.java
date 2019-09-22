package com.springboot;

import lombok.Data;
import org.springframework.context.ApplicationEvent;


/**
 * 定义事件
 */

@Data
public class MyEvent extends ApplicationEvent {

    String message;

    public MyEvent(Object source, String message){

        super(source);
        this.message = message;
    }
}
