package cn.sy.demo.service.impl;

import cn.sy.demo.conf.RabbitMqConfig;
import cn.sy.demo.service.RabbitMqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 与RabbitMqProducerServiceImpl2构成多生产者
 */
@Service("RabbitMqProducerServiceImpl1")
@Slf4j
public class RabbitMqProducerServiceImpl implements RabbitMqProducerService{

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String msg) {
        log.info("~~~生产消息1111~~~[{}]",msg);
        this.rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE,
                RabbitMqConfig.ROUTINGKEY, msg);
    }

}
