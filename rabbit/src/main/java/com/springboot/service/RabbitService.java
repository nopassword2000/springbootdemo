package com.springboot.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitService {

    @Autowired
    RabbitTemplate rabbitTemplate;


    public void SendMsg(String msg){
        try {
            //rabbitTemplate.send("myexchange","mq", new Message(msg.getBytes(), new MessageProperties()));
            rabbitTemplate.convertAndSend("myexchange","mq", msg);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

    }
}
