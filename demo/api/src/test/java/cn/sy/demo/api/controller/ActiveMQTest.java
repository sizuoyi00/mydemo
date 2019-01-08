package cn.sy.demo.api.controller;

import cn.sy.demo.model.User;
import cn.sy.demo.service.ActiveMqProducerService;
import cn.sy.demo.service.UserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import javax.annotation.Resource;

public class ActiveMQTest extends BaseControllerTest {

    @Resource
    private ActiveMqProducerService messageProducer;

    @Resource
    private UserService userService;

    @Test
    public void testActiveMqSend() {
        for (int x = 0; x < 5; x++) {
            this.messageProducer.sendMessage("study - " + x);
        }

    }

    @Test
    public void testActiveMqSend1() {
        User u= userService.get(33323L);
        final String jsonString = JSON.toJSONString(u);
        this.messageProducer.sendMessage(jsonString);
    }

}
