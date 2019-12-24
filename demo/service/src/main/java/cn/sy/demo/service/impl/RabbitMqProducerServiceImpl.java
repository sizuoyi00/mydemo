package cn.sy.demo.service.impl;

import cn.sy.demo.constant.MQConstant;
import cn.sy.demo.service.RabbitMqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 与RabbitMqProducerServiceImpl2构成多生产者
 * <p>
 * 部分延迟队列消费场景见RabbitMQTest
 */
@Service("RabbitMqProducerServiceImpl1")
@Slf4j
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService {

    @Resource
    private RabbitTemplate amqpTemplate;

    @Override
    public void sendMessage(String msg) {
        log.info("~~~生产消息beautify~~~[{}]", msg);
        System.out.println("~~~生产消息beautify~~~ " + msg);
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST, msg);
    }

    @Override
    public void sendMessageWithProperties(String msg) {
        log.info("~~~生产消息~~~[{}]", msg);
        System.out.println("~~~生产消息~~~ " + msg);
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST,
                msg, message -> {
                    // DeliveryMode是否持久化 2默认持久化  1不开启 服务重启后 消息不会被持久化
                    message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                    // 头部信息可个性化处理
                    message.getMessageProperties().setHeader("businessType", "order");
                    // message可透传，携带何种信息
                    message.getMessageProperties().setContentType("application/json");
                    message.getMessageProperties().setContentEncoding("utf-8");
                    return message;
                });
    }

    @Override
    public void sendPubConfirmsdMessage(String msg, String id) {
        log.info("~~~生产消息~~~[{}]", msg);
        System.out.println("~~~生产消息~~~ " + msg);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(id);
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST,
                msg, correlationData);
    }

}
