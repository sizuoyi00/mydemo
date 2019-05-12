package cn.sy.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息确认之一（Publisher Confirms）
 *
 * RabbitTemplate.ReturnCallback 不知道为什么不生效，取消使用
 *
 * RabbitTemplate.ConfirmCallback,消息确认监听器
 * RabbitTemplate.ReturnCallback,消息发送失败返回监听器
 *
 * 这里是确认有没有发送到消息中间件上，发送到中间件 ack=true
 * 这里不会关注消息是否有没有被消费，只关注有没有发送成功
 *
 *  如果消息没有到exchange,则confirm回调,ack=false
 *  如果消息到达exchange,则confirm回调,ack=true
 *  exchange到queue成功,则不回调return
 *  exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
 */
@Slf4j
@Component("amqpConfirmListener")
public class AmqpConfirmListener implements RabbitTemplate.ConfirmCallback{

    public AmqpConfirmListener(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * 这里是确认有没有发送到消息中间件上，发送到中间件 ack=true
     * 这里不会关注消息是否有没有被消费，只关注有没有发送成功
     * @param correlationData 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("收到回调，成功发送到broker,confirm--correlationData:" + correlationData + ",ack:" + ack + ",cause:"+cause);
        if(ack){
            System.out.println("消息id为: "+correlationData+"的消息，已经被ack成功");
        }else{
            System.out.println("消息id为: "+correlationData+"的消息，消息nack，失败原因是："+cause);
            log.error("消息id为: "+correlationData+"的消息，消息nack，失败原因是："+cause);

        }
    }

}