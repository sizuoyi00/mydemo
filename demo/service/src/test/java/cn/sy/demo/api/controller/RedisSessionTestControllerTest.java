package cn.sy.demo.api.controller;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class RedisSessionTestControllerTest extends BaseControllerTest{

    @Test
    public void create() throws Exception {

        MvcResult mvcResult = mvc.perform(post("/createSession").param("name", "hehe")).andReturn();
        prettyPrint(mvcResult);

    }

    @Test
    public void get() throws Exception {
        MvcResult mvcResult = mvc.perform(post("/getSession")).andReturn();
        prettyPrint(mvcResult);
    }


}
