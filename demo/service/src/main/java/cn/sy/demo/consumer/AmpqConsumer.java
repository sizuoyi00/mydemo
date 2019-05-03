package cn.sy.demo.consumer;

import cn.sy.demo.constant.MQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AmpqConsumer {

    /**
     * 多消费者消费
     * 默认exchange type = ExchangeTypes.DIRECT
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            key = {MQConstant.KEY_TEST},
            value = @Queue(value = MQConstant.QUEUE_TEST),
            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
    public void receiveMessage1(String msg){
        try {
            Thread.sleep(1000);
            log.info("~~~消费消息1111~~~[{}]",msg);
            System.out.println("~~~消费消息1111~~~"+msg);
        } catch (Exception e) {
            log.error("mq error:",e);
        }
    }

    @RabbitListener(bindings = @QueueBinding(
            key = {MQConstant.KEY_TEST},
            value = @Queue(value = MQConstant.QUEUE_TEST),
            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
    public void receiveMessage2(String msg) {
        try {
            Thread.sleep(1000);
            log.info("~~~消费消息2222~~~[{}]",msg);
            System.out.println("~~~消费消息2222~~~"+msg);
        } catch (Exception e) {
            log.error("mq error:",e);
        }

    }

}
