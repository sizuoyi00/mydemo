package cn.sy.demo.api.controller;

import cn.sy.demo.conf.RabbitMqConfig;
import cn.sy.demo.model.User;
import cn.sy.demo.service.RabbitMqProducerService;
import cn.sy.demo.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.amqp.core.AmqpTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RabbitMQTest extends BaseControllerTest{

    @Resource(name = "RabbitMqProducerServiceImpl1")
    private RabbitMqProducerService messageProducer;

    @Resource(name = "RabbitMqProducerServiceImpl2")
    private RabbitMqProducerService messageProducer2;

    @Resource
    private UserService userService;

    @Resource
    private AmqpTemplate amqpTemplate;

    @Test
    public void test() throws InterruptedException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        amqpTemplate.convertAndSend(RabbitMqConfig.ORDER_DELAY_EXCHANGE, RabbitMqConfig.ORDER_DELAY_ROUTING_KEY,
                "hahahaxixixi" + sdf.format(new Date()), message -> {
                    message.getMessageProperties().setExpiration(3 * 1000 + "");
                    return message;
                });

        Thread.sleep(5000);
    }

    @Test
    public void testSend() {
        for (int x = 0; x < 100; x++) {
            this.messageProducer.sendMessage("study sss- " + x);
        }
    }

    @Test
    public void testSendUser() {
        User u;
        for (int x = 0; x < 100; x++) {
            u= userService.get(33323L);
            this.messageProducer.sendMessage(JSON.toJSONString(u));
        }
    }

    @Test
    public void testSendUser2() {
        User u;
        for (int x = 0; x < 100; x++) {
            u= userService.get(33323L);
            this.messageProducer.sendMessage(JSON.toJSONString(u));
            messageProducer2.sendMessage(JSON.toJSONString(u)+x);
        }
    }

}
