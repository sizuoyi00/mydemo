package cn.sy.demo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 消息确认之一（Publisher Confirms）
 * <p>
 * RabbitTemplate.ConfirmCallback,消息确认监听器
 * RabbitTemplate.ReturnCallback,消息发送失败返回监听器
 * <p>
 * 这里是确认有没有发送到消息中间件上，发送到中间件 ack=true
 * 这里不会关注消息是否有没有被消费，只关注有没有发送成功
 * <p>
 * 如果消息没有到exchange,则confirm回调,ack=false
 * 如果消息到达exchange,则confirm回调,ack=true
 * exchange到queue成功,则不回调return
 * exchange到queue失败,则回调return(需设置mandatory=true,否则不回回调,消息就丢了)
 */
@Slf4j
@Component("amqpConfirmListener")
public class AmqpConfirmListener implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    public AmqpConfirmListener(RabbitTemplate rabbitTemplate) {
        rabbitTemplate.setConfirmCallback(this);
        // TODO 测试returncallback
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 这里是确认有没有发送到消息中间件上，发送到中间件 ack=true
     * 这里不会关注消息是否有没有被消费，只关注有没有发送成功
     *
     * @param correlationData 唯一标识，有了这个唯一标识，我们就知道可以确认（失败）哪一条消息了
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("收到回调，成功发送到broker,confirm--correlationData:" + correlationData + ",ack:" + ack + ",cause:" + cause);
        if (ack) {
            System.out.println("消息id为: " + correlationData + "的消息，已经被ack成功");
        } else {
            System.out.println("消息id为: " + correlationData + "的消息，消息nack，失败原因是：" + cause);
            log.error("消息id为: " + correlationData + "的消息，消息nack，失败原因是：" + cause);

        }
    }

    /**
     * 发送消息不到交换机或路由不到指定队列，不可达消息
     *
     * 设置生产端的mandatory属性会回调到生产者的ReturnListener，否则mq服务器会直接删除  TODO mandatory默认值不确定
     * 这个可以用来确认生产者发送失败的消息，可以落库后续补偿
     *
     * @param message    the returned message.
     * @param replyCode  the reply code.
     * @param replyText  the reply text.
     * @param exchange   the exchange.
     * @param routingKey the routing key.
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("发送消息到不到交换机/路由不到指定队列，消息不可达", message, replyCode, replyText, exchange, routingKey);
        //消息体
        message.getBody();
        //mq生成的唯一id
        message.getMessageProperties().getDeliveryTag();
        // DeliveryMode=Persistent=2持久的。
        // 在接收者接收消息并处理的时候会出现各种各样的问题：抛出异常导致与RabbitMQ连接断开，程序挂掉，网络问题等等。
        // 往往在出现这些问题的时候我们通常都希望队列能保存这些消息，并在程序再次起来的时候能够重新处理，
        // 或如果是负载均衡的模式下，能够把这个消息重新分配给其他的同等的接受者来处理。
        // 这同样也是RabbitMQ对消息持久化的一种功能。
        message.getMessageProperties().getDeliveryMode();

        log.warn("correlationId:{}",message.getMessageProperties().getCorrelationId());
        log.warn("replyText:{}",replyText);
        log.warn("消息内容:{}",new String(message.getBody()));
        log.warn("replyCode:{}",replyCode);
        log.warn("交换机:{}",exchange);
        log.warn("routingKey:{}",routingKey);
        log.info("需要更新数据库日志表得消息记录为不可达");

    }
}