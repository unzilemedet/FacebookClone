package com.unzile.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeName="auth-exchange";
    private String queueName="auth-queue-create-user";
    private String routingOrBindingKey="auth-routing-key-create-user";

    @Bean
    DirectExchange directAuthExchange(){
        return new DirectExchange(exchangeName);
    }
    /*Mesajların iletileceği ve dinleyenlerin okuyabileceği metodu tanımlıyoruz*/
    @Bean
    Queue authOueue(){
        return new Queue(queueName);
    }

    /*Kuyruk ile exchange arasında olan bağlantıyı kurmamız gerekiyor.Bunu yapmak için
    * binding nesnesine ihtiyaç var. */
    @Bean
    public Binding bindingAuthCreateUser(final Queue authQueue, final DirectExchange directAuthExchange){
        return BindingBuilder.bind(authQueue).to(directAuthExchange).with(routingOrBindingKey);
    }






}
