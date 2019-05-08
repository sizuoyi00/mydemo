package cn.sy.demo.service.impl;

import cn.sy.demo.constant.MQConstant;
import cn.sy.demo.service.RabbitMqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 与RabbitMqProducerServiceImpl2构成多生产者
 */
@Service("RabbitMqProducerServiceImpl1")
@Slf4j
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService{

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String msg) {
        log.info("~~~生产消息beautify~~~[{}]",msg);
        System.out.println("~~~生产消息beautify~~~ "+msg);
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST, msg);
    }

}
