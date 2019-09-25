package com.stackroute.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class IdeaHamsterRabbitMqConfig {

    @Value("${ideah.rabbitmq.queue}")
    String ideaQueue;
    @Value("${ideah.rabbitmq.exchange}")
    String ideaExchange;
    @Value("${ideah.rabbitmq.routingkey}")
    String ideaKey;

    @Bean
    Queue queueIdea(){
        return new Queue(ideaQueue,true);
    }
    @Bean
    TopicExchange exchangeIdea(){
        return new TopicExchange(ideaExchange);
    }
    @Bean
    Binding bindingIdea(Queue queueIdea,TopicExchange exchangeIdea){
        return BindingBuilder.bind(queueIdea).to(exchangeIdea).with(ideaKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }


    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Value("${ih.rabbitmq.queue}")
    String queueName;

    @Value("${ih.rabbitmq.exchange}")
    String exchange;

    @Value("${ih.rabbitmq.routingkey}")
    String routingkey;

    @Bean
    Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean
    TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }


}