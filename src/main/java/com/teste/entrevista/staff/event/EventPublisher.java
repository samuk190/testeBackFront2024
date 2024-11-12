package com.teste.entrevista.staff.event;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventPublisher {

    private final RabbitTemplate rabbitTemplate;

    public EventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCarCreatedEvent(String message) {
        rabbitTemplate.convertAndSend("car.created", message);
    }

    public void publishCustomerCreatedEvent(String message) {
        rabbitTemplate.convertAndSend("customer.created", message);
    }

    public void publishAppointmentCreatedEvent(String message) {
        rabbitTemplate.convertAndSend("appointment.created", message);
    }
}
