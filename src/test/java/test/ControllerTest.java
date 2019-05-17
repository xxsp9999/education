package test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author XieXing
 * @createDate 2019年5月1日 下午4:41:07
 * @description 单元测试，数据添加
 */
@RunWith(SpringJUnit4ClassRunner.class) // 指定单元测试类
@WebAppConfiguration // autowired WebApplicationContext
@ContextConfiguration(locations = { "classpath:spring-core.xml", "classpath:spring-mvc.xml",
		"classpath:spring-shiro.xml", "classpath:activiti-context.xml" }) // Spring的单元测试注解,指定配置文件所在位置,要将所有配置文件写全，否则会出现java.lang.IllegalStateException:
																			// Failed to load ApplicationContext 的异常
public class ControllerTest {

	// 传入SpringMvc的ioc
	@Autowired
	WebApplicationContext webApplicationContext;
	// 虚拟mvc请求，获取处理结果
	MockMvc mockMvc;

	@Before
	public void initMocMvc() {
		System.out.println("start------");
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void taskPageTest() throws Exception {
		//模拟请求拿到返回值
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/datashow/getStudentAnalyseData")).andReturn();
		//List<Leave> leaves = (List<Leave>) result.getRequest().getAttribute("xData");
		//System.out.println(leaves);
		System.out.println("success------");
	}
	
	@After
	public void end() {
		System.out.println("end------");
	}
}
