package com.jr.djt.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.github.pagehelper.PageInfo;
import com.jr.djt.beans.Crow;
import com.jr.djt.beans.MessageBean;
import com.jr.djt.beans.TableBean;

/**
 * 使用spring测试模块提供的测试请求功能，测试crud请求的正确性
 * spring4测试的时候需要servlet3.0的支持
 * @author qiuchen
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","classpath:springmvc.xml"})
public class MVCTest {
	//传入springmvc的ioc
	@Autowired//这个autowired只能注入ioc容器中的bean，如果需要注入自己需要添加一个注解@WebAppConfiguration,有了这个注解以后，就能把web自己的ioc容器注入进来
	WebApplicationContext context;
	
	
	//虚拟mvc请求，获取到处理结果
	private MockMvc mockMvc;
	/**
	 * 初始化MockMvc
	 */
	@Before//导入的是junit的注解，这个注解代表使用前调用这个方法
	public void initMockMvc(){
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		
	}
	@Test
	public void testPage() throws Exception{
		//模拟拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/workerById").param("pageNum", "1").param("u_num", "1")).andReturn();
		//请求成功后，请求域中会有pageInfo，我们取出pageInfo进行验证
		MockHttpServletRequest request = result.getRequest();
		MessageBean msg = (MessageBean) request.getAttribute("MessageBean");
		System.out.println(msg);
		/*System.out.println("当前页码:"+page.getPageNum());
		System.out.println("总页码:"+page.getPages());
		System.out.println("总记录数:"+page.getTotal());
		System.out.println("连续显示页码:");
		int[] nums = page.getNavigatepageNums();
		for (int i : nums) {
			System.out.println("页码："+i);
		}*/
		//System.out.println(msg.getCode());
	}
	@Test
	public void testable() throws Exception{
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/usetable/getusetable.do")).andReturn();
		MockHttpServletRequest request = result.getRequest();
		List<TableBean> list = (List<TableBean>) request.getAttribute("MessageBean");
		System.out.println(list);
	}
	
}
