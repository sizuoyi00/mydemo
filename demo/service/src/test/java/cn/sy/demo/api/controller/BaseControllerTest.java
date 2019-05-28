package cn.sy.demo.api.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public abstract class BaseControllerTest {
	@Autowired
	protected MockMvc mvc;
	
	protected void perform(MockHttpServletRequestBuilder builder) throws Exception {
		builder.header("X-Real-IP", "127.0.0.1");
		builder.accept(MediaType.parseMediaType("application/json;charset=UTF-8"));
		mvc.perform(builder).andReturn();
	}

	protected String prettyPrint(MvcResult result) throws UnsupportedEncodingException {
		String jsonResult = result.getResponse().getContentAsString();
		log.info(jsonResult);
		return jsonResult;
	}

}