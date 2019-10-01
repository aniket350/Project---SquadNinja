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
    //-----------------idea
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
//----------------------------sp
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

//------------------team
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
    Binding bindinvitedTeam(Queue appliedTeamQueue, TopicExchange appliedTeamExchange) {
        return BindingBuilder.bind(appliedTeamQueue).to(appliedTeamExchange).with(appliedTeamRoutingkey);
    }


    @Value("${invitedIdea.rabbitmq.queue}")
    String invitedTeamQueueName;

    @Value("${invitedIdea.rabbitmq.exchange}")
    String invitedTeamExchange;

    @Value("${invitedIdea.rabbitmq.routingkey}")
    String invitedTeamRoutingkey;

    @Bean
    Queue invitedTeamQueue() {
        return new Queue(invitedTeamQueueName, true);
    }

    @Bean
    TopicExchange invitedTeamExchange() {
        return new TopicExchange(invitedTeamExchange);
    }

    @Bean
    Binding bindAppliedTeam(Queue appliedTeamQueue, TopicExchange appliedTeamExchange) {
        return BindingBuilder.bind(appliedTeamQueue).to(appliedTeamExchange).with(invitedTeamRoutingkey);
    }

    //Worked on idea
    @Value("${workedOn.rabbitmq.queue}")
    String workedOnQueueName;
    @Value("${workedOn.rabbitmq.exchange}")
    String workedOnExchange;
    @Value("${workedOn.rabbitmq.routingkey}")
    String workedOnRoutingkey;
    @Bean
    Queue workedOnQueue() {
        return new Queue(workedOnQueueName, true);
    }
    @Bean
    TopicExchange workedOnExchange() {
        return new TopicExchange(workedOnExchange);
    }
    @Bean
    Binding bindWorkedOn(Queue workedOnQueue, TopicExchange workedOnExchange) {
        return BindingBuilder.bind(workedOnQueue).to(workedOnExchange).with(workedOnRoutingkey);
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