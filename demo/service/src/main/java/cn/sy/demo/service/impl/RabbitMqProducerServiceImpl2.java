package cn.sy.demo.service.impl;

import cn.sy.demo.constant.MQConstant;
import cn.sy.demo.service.RabbitMqProducerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("RabbitMqProducerServiceImpl2")
@Slf4j
public class RabbitMqProducerServiceImpl2 implements RabbitMqProducerService{

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    public void sendMessage(String msg) {
        log.info("~~~生产消息2222~~~[{}]",msg);
        System.out.println("~~~生产消息2222~~~ "+msg);
        amqpTemplate.convertAndSend(MQConstant.EXCHANGE_TEST, MQConstant.KEY_TEST, msg);
    }

}
