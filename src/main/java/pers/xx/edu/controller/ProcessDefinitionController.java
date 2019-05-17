package pers.xx.edu.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pers.xx.edu.utils.PageInfo;
import pers.xx.edu.utils.ResponseUtils;


/**
 * @author XieXing
 * @createDate 2019年4月23日 下午2:42:35
 * @description 流程定义管理
 */
@Controller
@RequestMapping("/processDefinition")
public class ProcessDefinitionController {
		
	@Autowired
	private RepositoryService repositoryService;

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 下午2:42:52
	 * @description 流程定义分页查询
	 * @param rows
	 * @param s_name
	 * @param page
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/processDefinitionPage")
	public JSONObject processDefinitionPage(String rows,String s_name,String page,HttpServletResponse response) throws Exception{
		if(s_name==null){
			s_name="";
		}
		PageInfo<ProcessDefinition> pageInfo=new PageInfo<>();
		Integer sizePage=Integer.parseInt(rows);
		pageInfo.setPageSize(sizePage);
		if(page==null||page.equals("")){
			page="1";
		}
		pageInfo.setPageIndex((Integer.parseInt(page) - 1)*sizePage);
		long count=repositoryService.createProcessDefinitionQuery()
				.processDefinitionNameLike("%"+s_name+"%")
				.count();
		List<ProcessDefinition> processDefinitionList=repositoryService.createProcessDefinitionQuery()
				.orderByDeploymentId().desc()
				.processDefinitionNameLike("%"+s_name+"%")
				.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"identityLinks","processDefinition"});
		JSONObject result=new JSONObject();
		JSONArray jsonArray=JSONArray.fromObject(processDefinitionList,jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", count);
		ResponseUtils.write(response, result);
		return null;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 下午2:44:58
	 * @description
	 * @param deploymentId
	 * @param diagramResourceName
	 * @param response 查看流程图
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/showView")
	public String showView(String deploymentId,String diagramResourceName,HttpServletResponse response)throws Exception{
		response.setContentType("image/jpeg");// 设置相应类型,告诉浏览器输出的内容为图片
		response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);
		InputStream inputStream=repositoryService.getResourceAsStream(deploymentId, diagramResourceName);
		OutputStream out=response.getOutputStream();
		for(int b=-1;(b=inputStream.read())!=-1;){
			out.write(b);
		}
		out.close();
		inputStream.close();
		return null;
	}

	
}
