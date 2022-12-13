package com.example.userservice.rabbitMq;

import com.example.userservice.dto.UserDtoRabbit;
import com.example.userservice.enums.ActionType;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Log4j2
public class UserPublisherRabbitMq {

    private final static String SENDING_MESSAGE_RABBITMQ = "Sending user %s through RabbitMq, with actionType: %s";
    private final static String FINISHING_MESSAGE_RABBITMQ = "User %s Sent";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value(value = "${rabbit.broker.exchange.userEvent}")
    private String exchangeUser;

    @Transactional
    public void publisherUser(UserDtoRabbit userDtoRabbit, ActionType actionType) {
        userDtoRabbit.setActionType(actionType);
        log.info(String.format(SENDING_MESSAGE_RABBITMQ, userDtoRabbit.getId(), actionType.toString()));
        rabbitTemplate.convertAndSend(exchangeUser, "", userDtoRabbit);
        log.info(String.format(FINISHING_MESSAGE_RABBITMQ, userDtoRabbit.getName()));
    }
}
