package cn.sy.demo.service;

public interface RabbitMqProducerService {

    void sendMessage(String msg) ;

    void sendMessageWithProperties(String msg);

    void sendPubConfirmsdMessage(String msg, String id);
}
