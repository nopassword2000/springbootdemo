package com.springboot.config;


import com.springboot.service.ChannelAwareMessageListenerImp;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.RabbitListenerConfigUtils;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.DirectMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean(name = RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME)
    public  RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry(){
        return new RabbitListenerEndpointRegistry();
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange("myexchange");
    }

    @Bean
    public Queue defaultQuee(){
        return new Queue("myqueue");
    }

    @Bean
    public Binding binding(){
        return BindingBuilder.bind(defaultQuee()).to(defaultExchange()).with("mq");
    }

    @Bean
    public AbstractMessageListenerContainer simpleMessageListenerContainer(@Autowired ConnectionFactory connectionFactory){
         DirectMessageListenerContainer container = new DirectMessageListenerContainer(connectionFactory);
        //SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setQueues(new Queue("myqueue"));
        container.setExposeListenerChannel(true);
        //设置最大的并发的消费者数量
        // container.setMaxConcurrentConsumers(10);
        //最小的并发消费者的数量
        //container.setConcurrentConsumers(1);
        return container;
    }
    @Bean
    public ChannelAwareMessageListener channelAwareMessageListener( @Autowired AbstractMessageListenerContainer container){

        ChannelAwareMessageListenerImp channelAwareMessageListener = new ChannelAwareMessageListenerImp();
        container.setMessageListener(channelAwareMessageListener);
        return channelAwareMessageListener;
    }

}
