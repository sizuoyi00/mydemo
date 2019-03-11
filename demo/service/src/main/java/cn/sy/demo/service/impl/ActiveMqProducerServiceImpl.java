//package cn.sy.demo.service.impl;
//
//import cn.sy.demo.service.ActiveMqProducerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jms.core.JmsMessagingTemplate;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.jms.Queue;
//
///**
// * MessageProducerServiceImpl
// * 消息发送者
// * @author sy
// */
//@Service
//@Slf4j
//public class ActiveMqProducerServiceImpl implements ActiveMqProducerService {
//    @Resource
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @Resource(name = "activeQueue")
//    private Queue queue;
//
//    @Override
//    public void sendMessage(String msg) {
//        log.info("~~~生产消息~~~[{}]",msg);
//        this.jmsMessagingTemplate.convertAndSend(this.queue, msg);
//    }
//
//}
