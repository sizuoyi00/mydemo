package cn.sy.demo.api.controller;

import cn.sy.demo.model.User;
import cn.sy.demo.service.RabbitMqProducerService;
import cn.sy.demo.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.annotation.Resource;

public class RabbitMQTest extends BaseControllerTest{

    @Resource(name = "RabbitMqProducerServiceImpl1")
    private RabbitMqProducerService messageProducer;

    @Resource(name = "RabbitMqProducerServiceImpl2")
    private RabbitMqProducerService messageProducer2;

    @Resource
    private UserService userService;

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
