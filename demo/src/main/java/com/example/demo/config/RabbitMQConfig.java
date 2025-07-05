package com.example.demo.config;


import com.example.demo.constants.APIConstants;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.ConnectException;

@Configuration
public class RabbitMQConfig{

    @Bean
    public Queue getQueue(){
        return new Queue(APIConstants.ORDERS_FOR_BILLING);
    }

    @Bean
    public TopicExchange getExchange(){
        return new TopicExchange(APIConstants.ORDER_EXCHANGE);
    }

    @Bean
    public Binding getBinding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with(APIConstants.ORDERS_ROUTING_KEY);
    }

    @Bean
    public Jackson2JsonMessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabitTemplate = new RabbitTemplate(connectionFactory);
        rabitTemplate.setMessageConverter(converter());
        return rabitTemplate;
    }
}
