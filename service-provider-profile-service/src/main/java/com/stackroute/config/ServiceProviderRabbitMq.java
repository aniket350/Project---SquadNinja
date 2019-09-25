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
/*@Configuration annotation indicates that a class declares one or more @Bean methods and may be processed by the Spring
container to generate bean definitions and service requests for those beans at runtime. */
@Configuration
public class ServiceProviderRabbitMq {

    @Value("${spRegister.rabbitmq.queue}")
    String spQueueName;

    @Value("${spRegister.rabbitmq.exchange}")
    String spExchange;

    @Value("${spRegister.rabbitmq.routingkey}")
    String spRoutingkey;

    @Bean
    Queue queueProfile() {
        return new Queue(spQueueName, true);
    }

    @Bean
    TopicExchange exchangeProfile() {
        return new TopicExchange(spExchange);
    }

    @Bean
    Binding bindingProfile(Queue queueProfile, TopicExchange exchangeProfile) {
        return BindingBuilder.bind(queueProfile).to(exchangeProfile).with(spRoutingkey);
    }

    @Value("${invitedIdea.rabbitmq.queue}")
    String invitedIdeaQueue;
    @Value("${invitedIdea.rabbitmq.exchange}")
    String invitedIdeaExchange;
    @Value("${invitedIdea.rabbitmq.routingkey}")
    String invitedIdeaRoutingKey;

    @Bean
    Queue queueInvitedIdea(){
        return new Queue(invitedIdeaQueue,true);
    }
    @Bean
    TopicExchange exchangeInvitedIdea(){
        return new TopicExchange(invitedIdeaExchange);
    }
    @Bean
    Binding bindingIdea(Queue queueInvitedIdea,TopicExchange exchangeInvitedIdea){
        return BindingBuilder.bind(queueInvitedIdea).to(exchangeInvitedIdea).with(invitedIdeaRoutingKey);
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

}
