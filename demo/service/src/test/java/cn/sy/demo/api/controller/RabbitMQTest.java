package cn.sy.demo.api.controller;

import cn.sy.demo.conf.RabbitMqDLXConfig;
import cn.sy.demo.conf.RabbitMqPluginConfig;
import cn.sy.demo.model.User;
import cn.sy.demo.service.RabbitMqProducerService;
import cn.sy.demo.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RabbitMQTest extends BaseTest {

    @Resource(name = "RabbitMqProducerServiceImpl1")
    private RabbitMqProducerService messageProducer;

    @Resource
    private UserService userService;

    @Resource
    private AmqpTemplate amqpTemplate;

    public static  final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 插件形式延迟队列测试
     */
    @Test
    public void testPlugin() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        amqpTemplate.convertAndSend(RabbitMqPluginConfig.DELAY_EXCHANGE_NAME, RabbitMqPluginConfig.DELAY_ROUTING_KEY,
                "plugintest11111" + sdf.format(new Date()), message -> {
                    message.getMessageProperties().setDelay(5 * 1000);
//            message.getMessageProperties().setHeader("x-delay", 5 * 1000);
                    //插件不可以使用下边这种方式，会被立即消费
//            message.getMessageProperties().setExpiration(3 * 1000 + "");
                    return message;
                });
    }

    /**
     * 死信形式延迟队列测试
     */
    @Test
    public void testdlx() {

        //延迟消费
        amqpTemplate.convertAndSend(RabbitMqDLXConfig.ORDER_DELAY_EXCHANGE, RabbitMqDLXConfig.ORDER_DELAY_ROUTING_KEY,
                "DLXtest11111" + DATE_FORMAT.format(new Date()), message -> {
                    message.getMessageProperties().setExpiration(3 * 1000 + "");
                    //ttl+dxl不可以使用以下两种方式，经测试会在消息队列中堆积，不会被消费，后边的也会堆积，不知道为什么，暂不研究
//                    message.getMessageProperties().setDelay(3000);
//                    message.getMessageProperties().setHeader("x-delay", 3 * 1000);
                    return message;
                });

    }

    @Test
    public void sendPubConfirmsdMessage() {
        this.messageProducer.sendPubConfirmsdMessage("sendPubConfirmsdMessage", UUID.randomUUID().toString());
    }

    @Test
    public void testSend() {
        this.messageProducer.sendMessage("study sss- sss");
    }

    @Test
    public void testSend2() {
        this.messageProducer.sendMessageWithProperties("study sss- sss" + DATE_FORMAT.format(new Date()));
    }

    @Test
    public void testSendUser() {
        User u;
        for (int x = 0; x < 10; x++) {
            u = userService.get(33323L);
            this.messageProducer.sendMessage(JSON.toJSONString(u));
        }
    }

//    @Test
//    public void testSendUser2() {
//        JwtUserDo u;
//        for (int x = 0; x < 100; x++) {
//            u= userService.get(33323L);
//            this.messageProducer.sendMessage(JSON.toJSONString(u));
//            messageProducer2.sendMessage(JSON.toJSONString(u)+x);
//        }
//    }

}
