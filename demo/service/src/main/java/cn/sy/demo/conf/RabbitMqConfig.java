package cn.sy.demo.conf;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMqConfig {

    public static final String EXCHANGE = "study.microboot.exchange"; // 交换空间名称
    public static final String ROUTINGKEY = "study.microboot.routingkey"; // 设置路由key
    public static final String QUEUE_NAME = "study.microboot.queue"; // 队列名称

    @Bean
    public Queue queue() { // 要创建的队列信息
        return new Queue(QUEUE_NAME);
    }

    /**
     * direct,fanout,topic,headers 四种交换器
     direct： 路由键完全匹配时，消息才投放到对应队列。AMQP实现都必须有一个direct交换器（默认交换器），名称为空白字符。队列不声明交换器，会自动绑定到默认交换器，队列的名称作为路由键。
     fanout：可以理解为广播，会把交换器上的所有消息投放到绑定到这个交换机上的队列上，无论这个队列是通过哪个路由键绑定到这个交换器上的
     topic：主题，使来自不同源头的消息到达同一个队列
     headers: 匹配消息头，其余与direct一样，实用性不大
     *
     * @return
     */
    @Bean
    public DirectExchange getDirectExchange() { // 使用直连的模式
//        exclusive：队列是应用程序私有的，auto-delete：最后一个消费者取消订阅时，队列会自动删除，durable：当前队列是否持久化
        return new DirectExchange(EXCHANGE, true, true);
    }

    /**
     * 路由的绑定
     * @param exchange
     * @param queue
     * @return
     */
    @Bean
    public Binding bindingExchangeQueue(DirectExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY) ;
    }

}
