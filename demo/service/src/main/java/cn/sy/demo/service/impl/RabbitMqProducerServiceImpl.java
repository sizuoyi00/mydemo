package cn.sy.demo.service.impl;

import cn.sy.demo.constant.MQConstant;
import cn.sy.demo.service.RabbitMqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 与RabbitMqProducerServiceImpl2构成多生产者
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
    public void sendMessageWithCorrelationData(String msg, CorrelationData correlationData) {
        log.info("~~~生产消息CorrelationData~~~[{}]", msg);
        System.out.println("~~~生产消息CorrelationData~~~ " + msg);
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST, msg, correlationData);
    }

    @Override
    public void sendPubConfirmsdMessage(String msg) {
        log.info("~~~生产消息~~~[{}]", msg);
        System.out.println("~~~生产消息~~~ " + msg);
        //new CorrelationData()这里参数实际换成id
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST,
                msg, new CorrelationData(msg));
    }

}
