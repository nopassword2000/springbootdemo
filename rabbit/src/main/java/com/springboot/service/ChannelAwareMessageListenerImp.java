package com.springboot.service;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;

//@Configuration
public class ChannelAwareMessageListenerImp implements ChannelAwareMessageListener {


    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        System.out.println("msg=======>" + new String (message.getBody()));
    }

    @Override
    public void onMessage(Message message) {
        System.out.println(message.getBody());
    }
}
