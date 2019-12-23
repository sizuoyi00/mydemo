package cn.sy.demo.conf;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class RabbitMqDLXConfig {

    /**
     * 延迟队列 TTL 名称
     */
    public static final String ORDER_DELAY_QUEUE = "user.order.delay.queue";
    /**
     * DLX，dead letter发送到的 exchange
     * 延时消息就是发送到该交换机
     */
    public static final String ORDER_DELAY_EXCHANGE = "user.order.delay.exchange";
    /**
     * routing key 名称
     * 具体消息发送在该 routingKey
     */
    public static final String ORDER_DELAY_ROUTING_KEY = "order_delay";

    /**
     * 死信会交给真正处理的交换机，路由，队列
     */
    public static final String ORDER_QUEUE_NAME = "user.order.queue";
    public static final String ORDER_EXCHANGE_NAME = "user.order.exchange";
    public static final String ORDER_ROUTING_KEY = "order";

    /**
     * 延迟队列配置
     * <p>
     * 1、params.put("x-message-ttl", 5 * 1000);
     * 第一种方式是直接设置 Queue 延迟时间 但如果直接给队列设置过期时间,这种做法不是很灵活,（当然二者是兼容的,默认是时间小的优先）
     * 2、rabbitTemplate.convertAndSend(book, message -> {
     * message.getMessageProperties().setExpiration(2 * 1000 + "");
     * return message;
     * });
     * 第二种就是每次发送消息动态设置延迟时间,这样我们可以灵活控制
     * <p>
     * 重要思想：
     * 只有当原消费队列ORDER_DELAY_QUEUE 不被消费者监听，使得消息不被消费的情况，
     * 也就是要先满足ttl条件
     * 才会在设定ttl时间没被消费才会被打入到dlx中，
     * 所以，一定不要监听原消费队列ORDER_DELAY_QUEUE，这样会被直接消费掉的
     **/
    @Bean
    public Queue delayOrderQueue() {

//        return QueueBuilder.durable()
//                .withArgument("x-dead-letter-exchange", ORDER_EXCHANGE_NAME)
//                .withArgument("x-dead-letter-routing-key", ORDER_ROUTING_KEY)
//                .build();

        Map<String, Object> params = new HashMap<>();
        // x-dead-letter-exchange 声明了队列里的死信 "转发到的DLX死信交换机名称" 也就是真正处理的交换机
        params.put("x-dead-letter-exchange", ORDER_EXCHANGE_NAME);
        // x-dead-letter-routing-key 声明了这些死信在 "转发时携带的 routing-key 名称" 也就是真正处理的路由
        params.put("x-dead-letter-routing-key", ORDER_ROUTING_KEY);
        return new Queue(ORDER_DELAY_QUEUE, true, false, false, params);
    }

    /**
     * /**
     * direct,fanout,topic,headers 四种交换器
     * direct： 路由键完全匹配时，消息才投放到对应队列。AMQP实现都必须有一个direct交换器（默认交换器），名称为空白字符。队列不声明交换器，会自动绑定到默认交换器，队列的名称作为路由键。
     * fanout：可以理解为广播，会把交换器上的所有消息投放到绑定到这个交换机上的队列上，无论这个队列是通过哪个路由键绑定到这个交换器上的
     * topic：主题，使来自不同源头的消息到达同一个队列
     * headers: 匹配消息头，其余与direct一样，实用性不大
     * <p>
     * #：相当于一个或者多个单词，例如一个匹配模式是topic.#，那么，以topic开头的路由键都是可以的
     * *：相当于一个单词，例如一个匹配模式是topic.*，那么，以topic开头的路由键,后面接一个单词的都可以
     */
    @Bean
    public DirectExchange orderDelayExchange() {
//        return (DirectExchange) ExchangeBuilder.directExchange("orderDelayExchange").durable(true).build();
        return new DirectExchange(ORDER_DELAY_EXCHANGE);
    }

    @Bean
    public Binding dlxBinding() {
        return BindingBuilder.bind(delayOrderQueue()).to(orderDelayExchange()).with(ORDER_DELAY_ROUTING_KEY);
    }


    @Bean
    public Queue orderQueue() {
        return new Queue(ORDER_QUEUE_NAME, true);
    }

    /**
     * 将路由键和某模式进行匹配。此时队列需要绑定要一个模式上。
     * 符号“#”匹配一个或多个词，符号“*”匹配不多不少一个词。因此“audit.#”能够匹配到“audit.irs.corporate”，但是“audit.*” 只会匹配到“audit.irs”。
     **/
    @Bean
    public DirectExchange orderDirectExchange() {
        //durable:表示消息是否可持久化
        //autoDelete:表示若没有队列和此交换机绑定 就直接删除该交换机
//        return new DirectExchange(ORDER_EXCHANGE_NAME, true, false);
//        return (DirectExchange) ExchangeBuilder.directExchange(ORDER_EXCHANGE_NAME).durable(true).build();
        return new DirectExchange(ORDER_EXCHANGE_NAME);
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderDirectExchange()).with(ORDER_ROUTING_KEY);
    }

}
