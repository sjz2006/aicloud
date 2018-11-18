package com.cn.spring.megoai;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * ctr单元测试
 * 
 * @author 苏坚昭
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MegoaiApplicationCtrlTests {

	@Autowired
	private WebApplicationContext wac;

	/**
	 * MockMvc实现了对Http请求的模拟，能够直接使用网络的形式，转换到Controller的调用，这样可以使得测试速度快、不依赖网络环境，而且提供了一套验证的工具
	 */
	private MockMvc mvc;

	private MockHttpSession session;

	@Before
	public void setupMockMvc() {
		// 初始化MockMvc对象
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
		session = new MockHttpSession();
		// 拦截器那边会判断用户是否登录，所以这里注入一个用户
		// User user =new User("root","root");
		// session.setAttribute("user",user);
	}

	/**
	 * 新增教程测试用例
	 * 
	 * @throws Exception
	 */
	@Test
	public void addLearn() throws Exception {
		String json = "{\"author\":\"HAHAHAA\",\"title\":\"Spring\",\"url\":\"http://tengj.top/\"}";
		// 执行一个请求
		mvc.perform(MockMvcRequestBuilders.post("/learn/add").accept(MediaType.APPLICATION_JSON_UTF8)
				.content(json.getBytes()) // 传json参数
				.session(session)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 获取教程测试用例
	 * 
	 * @throws Exception
	 */
	@Test
	public void qryLearn() throws Exception {
		// 构造一个请求，Post请求就用.post方法
		mvc.perform(MockMvcRequestBuilders.get("/learn/resource/1001").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8).session(session))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.author").value("嘟嘟MD独立博客"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot干货系列"))
				.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 修改教程测试用例
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateLearn() throws Exception {
		String json = "{\"author\":\"测试修改\",\"id\":1031,\"title\":\"Spring Boot干货系列\",\"url\":\"http://tengj.top/\"}";
		mvc.perform(MockMvcRequestBuilders.post("/learn/update").accept(MediaType.APPLICATION_JSON_UTF8)
				.content(json.getBytes())// 传json参数
				.session(session)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 删除教程测试用例
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteLearn() throws Exception {
		String json = "[1031]";
		mvc.perform(MockMvcRequestBuilders.post("/learn/delete").accept(MediaType.APPLICATION_JSON_UTF8)
				.content(json.getBytes())// 传json参数
				.session(session)).andExpect(MockMvcResultMatchers.status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

}
