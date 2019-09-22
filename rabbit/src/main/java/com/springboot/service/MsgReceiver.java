package com.springboot.service;

import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "myqueue")
public class MsgReceiver {

  //  @RabbitHandler
    public void process(String content) {
        System.out.println(content);
    }
}
