package com.springboot;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@EnableRabbit
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    RabbitListenerEndpointRegistry rabbitListenerEndpointRegistry;

    @Autowired
    ConnectionFactory connectionFactory;


    @Autowired
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //RabbitBootstrapConfiguration rabbitBootstrapConfiguration = applicationContext.getBean(RabbitBootstrapConfiguration.class);
        //RabbitListenerConfigurationSelector rabbitListenerConfigurationSelector  = applicationContext.getBean(RabbitListenerConfigurationSelector.class);

        System.out.println("helo-init");
/*        SimpleRabbitListenerEndpoint simpleRabbitListenerEndpoint = new SimpleRabbitListenerEndpoint();
        SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
        simpleRabbitListenerContainerFactory.setConnectionFactory(connectionFactory);
        simpleRabbitListenerEndpoint.setMessageListener( new MessageListenerAdapter());
        simpleRabbitListenerEndpoint.setId("mymq");
        simpleRabbitListenerEndpoint.setGroup("groupmq");
        rabbitListenerEndpointRegistry.registerListenerContainer(simpleRabbitListenerEndpoint, simpleRabbitListenerContainerFactory);*/

    }
}
