package com.stackroute.teammanagementservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Rabbit Configuration
@Configuration
public class RabbitMQConfig {
    @Value("${ideat.rabbitmq.queue}")
    String queueName;
    @Value("${ideat.rabbitmq.exchange}")
    String exchange;
    @Value("${ideat.rabbitmq.routingkey}")
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


    @Value("${appliedTeam.rabbitmq.queue}")
    String appliedTeamQueueName;

    @Value("${appliedTeam.rabbitmq.exchange}")
    String appliedTeamExchange;

    @Value("${appliedTeam.rabbitmq.routingkey}")
    String appliedTeamRoutingkey;

    @Bean
    Queue appliedTeamQueue() {
        return new Queue(appliedTeamQueueName, true);
    }

    @Bean
    TopicExchange appliedTeamExchange() {
        return new TopicExchange(appliedTeamExchange);
    }

    @Bean
    Binding bindAppliedTeam(Queue appliedTeamQueue, TopicExchange appliedTeamExchange) {
        return BindingBuilder.bind(appliedTeamQueue).to(appliedTeamExchange).with(appliedTeamRoutingkey);
    }



    @Value("${emailt.rabbitmq.queue}")
    String emailTeamQueueName;

    @Value("${emailt.rabbitmq.exchange}")
    String emaileTeamExchange;

    @Value("${appliedTeam.rabbitmq.routingkey}")
    String emailTeamRoutingkey;

    @Bean
    Queue emailTeamQueue() {
        return new Queue(emailTeamQueueName, true);
    }

    @Bean
    TopicExchange emailTeamExchange() {
        return new TopicExchange(emaileTeamExchange);
    }

    @Bean
    Binding bindEmailTeam(Queue emailTeamQueue, TopicExchange emailTeamExchange) {
        return BindingBuilder.bind(emailTeamQueue).to(emailTeamExchange).with(emailTeamRoutingkey);
    }


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory =new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }
    @Bean
    public RabbitTemplate rabbitMQTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}