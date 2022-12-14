package com.example.controleservice.rabbitMq;

import com.example.controleservice.dto.UserDtoRabbit;
import com.example.controleservice.enums.EActionType;
import com.example.controleservice.models.Student;
import com.example.controleservice.service.StudentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class UserConsummer {

    public static final String USER_SAVED_IN_DATA_BASE = "User %s saved in dataBase!! ";
    public static final String USER_UPDATED_IN_DATA_BASE = "User %s updated with sucessfull!! ";
    public static final String USER_DELETED_IN_DATA_BASE = "User %s deleted with sucessfull!! ";

    @Autowired
    private StudentService studentService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${rabbit.broker.queue.user.name}", durable = "true"),
            exchange = @Exchange(value = "${rabbit.broker.exchange.userEvent}", type = ExchangeTypes.FANOUT)
    ))
    public void listenUserEvent(@Payload UserDtoRabbit userDtoRabbit) {
        var fullNameUser = userDtoRabbit.getName().concat(" ").concat(userDtoRabbit.getLastName());
        log.info("User received");
        var userModel = new Student();
        BeanUtils.copyProperties(userDtoRabbit, userModel);

        switch (EActionType.valueOf(userDtoRabbit.getEActionType().getDescription())) {
            case CREATE:
                studentService.saveStudent(userModel);
                log.info(String.format(USER_SAVED_IN_DATA_BASE, fullNameUser));
                break;
            case UPDATE:
                studentService.updateStudent(userModel);
                log.info(String.format(USER_UPDATED_IN_DATA_BASE, fullNameUser));
                break;
            case DELETE:
                studentService.deleteStudent(userModel);
                log.info(String.format(USER_DELETED_IN_DATA_BASE, fullNameUser));
                break;
        }
    }
}
