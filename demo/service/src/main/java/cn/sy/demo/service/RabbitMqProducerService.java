package cn.sy.demo.service;

import org.springframework.amqp.rabbit.connection.CorrelationData;

public interface RabbitMqProducerService {

    void sendMessage(String msg) ;

    void sendMessageWithCorrelationData(String msg, CorrelationData correlationData);

    void sendPubConfirmsdMessage(String msg);
}
