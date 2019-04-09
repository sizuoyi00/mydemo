package com.jincou;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * 功能描述：测试mockmvc类
 *
 */
@RunWith(SpringRunner.class)  //底层用junit  SpringJUnit4ClassRunner
@SpringBootTest(classes={SpringbootstudyApplication.class}) //启动整个springboot工程
@AutoConfigureMockMvc 
public class MockMvcTestDemo {

	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void apiTest() throws Exception {
		
		MvcResult mvcResult =  mockMvc.perform( MockMvcRequestBuilders.get("/vq/test") ).
				andExpect( MockMvcResultMatchers.status().isOk() ).andReturn();
		int status = mvcResult.getResponse().getStatus();
		System.out.println(status);
		
	}
}
