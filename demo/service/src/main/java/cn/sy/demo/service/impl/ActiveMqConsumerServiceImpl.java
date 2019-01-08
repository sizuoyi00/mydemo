package cn.sy.demo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

/**
 * MessageConsumerServiceImpl
 * 消息消费者:进行监听控制
 * @author sy
 */
@Service
@Slf4j
public class ActiveMqConsumerServiceImpl  {

    @JmsListener(destination="study.msg.queue")
    public void sendMessage(String msg) {

        log.info("~~~消费消息~~~[{}]",msg);

    }

}

