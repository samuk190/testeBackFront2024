package com.teste.entrevista.staff.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue carCreatedQueue() {
        return new Queue("car.created", true);
    }

    @Bean
    public Queue customerCreatedQueue() {
        return new Queue("customer.created", true);
    }

    @Bean
    public Queue appointmentCreatedQueue() {
        return new Queue("appointment.created", true);
    }
}
