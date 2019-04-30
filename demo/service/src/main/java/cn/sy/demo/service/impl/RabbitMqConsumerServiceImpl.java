package cn.sy.demo.service.impl;

import cn.sy.demo.conf.RabbitMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * RabbitMqConsumerServiceImpl
 * 消息消费者:进行监听控制
 * @author sy
 */
@Service
@Slf4j
public class RabbitMqConsumerServiceImpl{

    /**
     * 多消费者消费
     * @param msg
     * @throws InterruptedException
     */
    @RabbitListener(queues= RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage1(String msg) throws InterruptedException {
        Thread.sleep(1000);
        log.info("~~~消费消息1111~~~[{}]",msg);
        System.out.println("~~~消费消息1111~~~"+msg);
    }

    @RabbitListener(queues= RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage2(String msg) throws InterruptedException {
        Thread.sleep(1000);
        log.info("~~~消费消息2222~~~[{}]",msg);
        System.out.println("~~~消费消息2222~~~"+msg);
    }

}

