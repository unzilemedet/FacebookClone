package com.unzile.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/*!!!DİKKAT!!!!
* Bu model üzerinden RabbitMQ ya mesaj ileteceğiz ve bu mesajı kuyruğa işlenmesini
* iateyeceğiz.Burada gönderilecek olan sınıf bilgisi rabbitmq ya base64 olarak
* iletilecektir.Bu nedenle , sınıfın serileştirilmesi gerekmektedir . Buna 
*
*
* */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserModel implements Serializable {
    Long authid;
    String username;
    String email;

}
