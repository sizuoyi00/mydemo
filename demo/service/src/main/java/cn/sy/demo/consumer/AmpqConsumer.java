package cn.sy.demo.consumer;

import cn.sy.demo.conf.RabbitMqDLXConfig;
import cn.sy.demo.conf.RabbitMqPluginConfig;
import cn.sy.demo.constant.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
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

    /**
     * 插件延迟消费
     * @param msg
     */
    @RabbitListener(queues = RabbitMqPluginConfig.DELAY_QUEUE_NAME)
    public void receivePluginMessage(String msg) {
        log.info("~~~消费消息 plugin DELAY_QUEUE_NAME~~~"+msg);
        System.out.println(new Date() + "~~~消费消息 plugin DELAY_QUEUE_NAME~~~"+msg);
    }


    /**
     * 正常队列 简洁写法
      * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            key = {MQConstant.KEY_TEST},
            value = @Queue(value = MQConstant.QUEUE_TEST),
            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
    public void receiveMessage1(String msg){
        try {
            log.info("~~~消费消息beautify~~~[{}]",msg);
            System.out.println("~~~消费消息beautify~~~"+msg);
        } catch (Exception e) {
            log.error("mq error:",e);
        }
    }

}
