package cn.sy.demo.service;

public interface RabbitMqProducerService {

    void sendMessage(String msg) ;

    void sendMessageWithProperties(String msg);

    void sendPubConfirmsdMessage(String msg, String id);

    void sendPubConfirmErrorMessage(String msg, String id);

    void sendPubReturnErrorMessage(String msg, String id);
}
