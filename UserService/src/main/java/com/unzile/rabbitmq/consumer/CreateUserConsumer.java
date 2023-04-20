package com.unzile.rabbitmq.consumer;

import com.unzile.rabbitmq.model.CreateUserModel;
import com.unzile.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserProfileService userProfileService;

    @RabbitListener(queues = "auth-queue-create-user")

        public void createUserfromHandleQueue(CreateUserModel userModel){
        userProfileService.save(userModel);
        System.out.println("kullanıcı oluşturuldu");

        }
}
