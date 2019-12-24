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
public class AmqpConsumer {


    /**
     * 延迟队列~ttl+dlx重要思想：
     * 只有当原消费队列ORDER_DELAY_QUEUE 不被消费者监听，使得消息不被消费的情况，
     * 才会在设定ttl时间后被打入到dlx中，所以，一定不要监听原消费队列，这样会被直接消费掉的
     *
     * @param msg
     */
    @RabbitListener(queues = RabbitMqDLXConfig.ORDER_QUEUE_NAME)
    public void receivedlxMessage(String msg) {
        log.info("~~~消费消息 DLX ORDER_QUEUE_NAME~~~" + msg);
        System.out.println(new Date() + "~~~消费消息 DLX ORDER_QUEUE_NAME~~~" + msg);
    }

    /**
     * 插件延迟消费
     *
     * @param msg
     */
    @RabbitListener(queues = RabbitMqPluginConfig.DELAY_QUEUE_NAME)
    public void receivePluginMessage(String msg) {
        log.info("~~~消费消息 plugin DELAY_QUEUE_NAME~~~" + msg);
        System.out.println(new Date() + "~~~消费消息 plugin DELAY_QUEUE_NAME~~~" + msg);
    }


    /**
     * 正常队列 简洁写法
     * chanel式消费者的用法
     *
     * //mq生成的唯一id
     * long deliveryTag = messageProperties.getDeliveryTag();
     *
     * MANUAL手动确认
     * //确认收到消息，手动确认机制，同下边AUTO手动的不抛异常 自动确认
     * channel.basicAck(deliveryTag, false);
     * //拒绝一个方法，同下边两个异常的处理
     * channel.basicReject(deliveryTag, false);
     * @param msg
     */
//    @RabbitListener(bindings = @QueueBinding(
//            key = {MQConstant.KEY_TEST},
//            value = @Queue(value = MQConstant.QUEUE_TEST),
//            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
//    public void receiveMessage1(String msg, @Headers Map<String, Object> headers, Message message, Channel channel) {
//
//        try {
//            MessageProperties messageProperties = message.getMessageProperties();
//
//            //mq生成的唯一id
//            long deliveryTag = messageProperties.getDeliveryTag();
//            // 消息体
//            byte[] body = message.getBody();
//
//            //确认收到消息，手动确认机制，同下边的不抛异常 自动确认
//            channel.basicAck(deliveryTag, false);
//
//            //拒绝一个方法，同下边两个异常的处理
//            channel.basicReject(deliveryTag, false);
//
//            log.info("~~~消费消息beautify~~~[{}]", msg);
//            System.out.println("~~~消费消息beautify~~~" + msg);
//        } catch (Exception e) {
//            log.error("mq error:", e);
//        }
//    }

    /**
     * 消息确认之二（Consumer Acknowledgements）
     * AcknowledgeMode.MANUAL 手动确认 见上方basicAck，basicReject
     * AcknowledgeMode.AUTO 根据方法的执行情况来决定是否确认还是拒绝（是否重新入queue）
     * 如果消息成功被消费（成功的意思就是在消费的过程中没有抛出异常），则自动确认。
     * <p>
     * 1）当抛出AmqpRejectAndDontRequeueException异常的时候，则消息会被拒绝，且requeue=false（不重新入队列）
     * 2）当抛出ImmediateAcknowledgeAmqpException异常，则消费者会被确认 重新入队列
     * 3）其他的异常，则消息会被拒绝，且requeue=true（如果此时只有一个消费者监听该队列，则有发生死循环的风险，
     * 多消费端也会造成资源的极大浪费，这个在开发过程中一定要避免的）。可以通过setDefaultRequeueRejected（默认是true）去设置，
     * <p>
     * 根据上述异常 对所处理的业务进行 按需抛出
     *
     * 消息拉取数量限制取决于prefetch参数，TODO 未确认
     *
     * @param msg
     * @return
     */
    @RabbitListener(bindings = @QueueBinding(
            key = {MQConstant.KEY_TEST},
            value = @Queue(value = MQConstant.QUEUE_TEST),
            exchange = @Exchange(value = MQConstant.EXCHANGE_TEST)))
    public void receiveAutoMessageAck(String msg) {
        log.info("~~~消费消息beautify~~~[{}]", msg);
        System.out.println("~~~消费消息beautify~~~" + msg);

        //抛出NullPointerException异常 属于其他异常  进入重试，达到重试次数后结束
        //throw new NullPointerException("消息消费失败");

        //当抛出的异常是AmqpRejectAndDontRequeueException异常的时候，则消息会被拒绝，且requeue=false  不重试
        //throw new AmqpRejectAndDontRequeueException("消息消费失败");

        //当抛出ImmediateAcknowledgeAmqpException异常，则消费者会被确认--进入重试，达到重试次数后结束
        //throw new ImmediateAcknowledgeAmqpException("消息消费失败");
    }

}
