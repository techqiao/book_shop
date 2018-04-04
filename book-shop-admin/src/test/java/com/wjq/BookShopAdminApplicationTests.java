package com.wjq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookShopAdminApplication.class)
public class BookShopAdminApplicationTests {

	@Autowired
	private WebApplicationContext wac;
	//mockMvc用来测试mvc框架
	private MockMvc mockMvc;

	//在测试用例之前，创建一个模拟mvc的mockMvc的环境
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void whenBookQuerySuccess() throws Exception {
		//Assert.assertEquals();
		String result = mockMvc.perform(get("/book?page=1&sort=name,desc&sort=createTime,asc")
				.param("name", "kobe")//模拟前端传的参数和值
//                .param("categoryId","1")
//                .param("page","1")//分页信息
//                .param("size","5")//每页数量
//                .param("sort","name,desc","createTime,asc")//排序
				.accept(MediaType.APPLICATION_JSON_UTF8))//get 请求/book json
				.andExpect(status().isOk())//期望的状态是ok的返回200
				.andExpect(jsonPath("$.length()").value(0))//期望返回的是集合3个值
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/book/1").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.name").value("战争与和平"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);
	}

	@Test
	public void whenGetInfoError() throws Exception {
		mockMvc.perform(get("/book/10").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().is4xxClientError());
	}

	@Test
	public void whenCreateSuccess() throws Exception {
		String context = "{\"id\":null,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-12-18\"}";
		mockMvc.perform(post("/book").content(context).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"));
	}

	@Test
	public void whenUpdateSuccess() throws Exception {
		String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-12-18\"}";
		mockMvc.perform(put("/book/1").content(context).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"));
	}

	@Test
	public void whenDeleteSuccess() throws Exception {
		//String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-12-18\"}";
		mockMvc.perform(delete("/book/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void whenCookieOrHerderSuccess() throws Exception {
		//String context = "{\"id\":1,\"name\":\"战争与和平\",\"context\":null,\"publishDate\":\"2017-12-18\"}";
		mockMvc.perform(get("/book/1")
				.cookie(new Cookie("token","123456"))
				.header("author","123456789")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void whenUploadSuccess() throws Exception {
		String result = mockMvc.perform(fileUpload("/file/upload")//执行一个请求
				//接受的参数，文件名，表单上传的contentType,文件的内容
				.file(new MockMultipartFile("file","testFile.test","multipart/form-data","hello kobe".getBytes("UTF-8"))))
				.andExpect(status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(result);

	}

}
