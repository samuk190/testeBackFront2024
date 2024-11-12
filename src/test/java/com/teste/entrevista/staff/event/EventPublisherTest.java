package com.teste.entrevista.staff.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.verify;

public class EventPublisherTest {

    @Mock
    private RabbitTemplate rabbitTemplate;

    @InjectMocks
    private EventPublisher eventPublisher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldPublishCarCreatedEvent() {
        String message = "New car created with ID: 1";

        eventPublisher.publishCarCreatedEvent(message);

        verify(rabbitTemplate).convertAndSend("car.created", message);
    }

    @Test
    void shouldPublishCustomerCreatedEvent() {
        String message = "New customer created with ID: 1";

        eventPublisher.publishCustomerCreatedEvent(message);

        verify(rabbitTemplate).convertAndSend("customer.created", message);
    }

    @Test
    void shouldPublishAppointmentCreatedEvent() {
        String message = "New appointment created with ID: 1";

        eventPublisher.publishAppointmentCreatedEvent(message);

        verify(rabbitTemplate).convertAndSend("appointment.created", message);
    }
}
