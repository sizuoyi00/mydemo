package cn.sy.demo.conf.mq;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;

/**
 * ActiveMQConfig
 * 配置程序类，定义 ActiveMQ 的消息发送模版处理类
 * @author sy
 */
@Configuration
@EnableJms
public class ActiveMQConfig {

    @Bean(name = "activeQueue")
    public Queue queue() {
        return new ActiveMQQueue("study.msg.queue");
    }

}
