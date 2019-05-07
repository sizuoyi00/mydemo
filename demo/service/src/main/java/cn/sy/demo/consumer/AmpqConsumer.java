package cn.sy.demo.consumer;

import cn.sy.demo.conf.RabbitMqDLXConfig;
import cn.sy.demo.conf.RabbitMqPluginConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class AmpqConsumer {


    /**
     * 延迟队列~ttl+dlx重要思想：
     * 只有当原消费队列ORDER_DELAY_QUEUE 不被消费者监听，使得消息不被消费的情况，
     * 才会在设定ttl时间后被打入到dlx中，所以，一定不要监听原消费队列，这样会被直接消费掉的
     * @param msg
     */
    @RabbitListener(queues= RabbitMqDLXConfig.ORDER_QUEUE_NAME)
    public void receivedlxMessage(String msg){
        log.info("~~~消费消息 DLX ORDER_QUEUE_NAME~~~"+msg);
        System.out.println(new Date() + "~~~消费消息 DLX ORDER_QUEUE_NAME~~~"+msg);
    }

    @RabbitListener(queues = RabbitMqPluginConfig.DELAY_QUEUE_NAME)
    public void receive(String msg) {
        log.info("~~~消费消息 plugin DELAY_QUEUE_NAME~~~"+msg);
        System.out.println(new Date() + "~~~消费消息 plugin DELAY_QUEUE_NAME~~~"+msg);
    }


//    @RabbitListener(bindings = @QueueBinding(
//            key = {MQConstant.KEY_TEST},
//            value = @Queue(value = MQConstant.QUEUE_TEST),
//            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
//    public void receiveMessage1(String msg){
//        try {
//            Thread.sleep(1000);
//            log.info("~~~消费消息1111~~~[{}]",msg);
//            System.out.println("~~~消费消息1111~~~"+msg);
//        } catch (Exception e) {
//            log.error("mq error:",e);
//        }
//    }

//    @RabbitListener(bindings = @QueueBinding(
//            key = {MQConstant.KEY_TEST},
//            value = @Queue(value = MQConstant.QUEUE_TEST),
//            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
//    public void receiveMessage2(String msg) {
//        try {
//            Thread.sleep(1000);
//            log.info("~~~消费消息2222~~~[{}]",msg);
//            System.out.println("~~~消费消息2222~~~"+msg);
//        } catch (Exception e) {
//            log.error("mq error:",e);
//        }
//
//    }

//    @RabbitListener(bindings = @QueueBinding(
//            key = {MQConstant.DELAY_KEY_TEST},
//            value = @Queue(value = MQConstant.DELAY_QUEUE_TEST),
//            exchange = @Exchange(value = MQConstant.DELAY_EXCHANGE_TEST)))
//    public void delayMessage(String msg) {
//        try {
//            Thread.sleep(1000);
//            log.info("~~~消费消息3333~~~[{}]",msg);
//            System.out.println("~~~消费消息3333~~~"+msg);
//        } catch (Exception e) {
//            log.error("mq error:",e);
//        }
//
//    }

}
