package pers.xx.edu.controller;

import java.io.IOException;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pers.xx.edu.utils.DateJsonValueProcessor;
import pers.xx.edu.utils.PageInfo;
import pers.xx.edu.utils.ResponseInfo;
import pers.xx.edu.utils.ResponseUtils;

/**
 * @author XieXing
 * @createDate 2019年4月21日 上午10:37:58
 * @description 流程部署管理
 */
@Controller
@RequestMapping("/deploy")
public class DeployController {

	@Autowired
	private RepositoryService repositoryService;

	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 上午10:32:16
	 * @description
	 * @param response
	 * @param deployFile
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addDeploy")
	public ResponseInfo addDeploy(HttpServletResponse response, MultipartFile deployFile) {
		String filename = deployFile.getOriginalFilename();
		String deployName = filename.substring(0, filename.lastIndexOf("."));
		try {
			repositoryService.createDeployment() // 创建部署
					.name(deployName) // 需要部署流程名称
					.addZipInputStream(new ZipInputStream(deployFile.getInputStream()))// 添加ZIP输入流
					.deploy();// 开始部署
		} catch (IOException e) {
			return new ResponseInfo(false, "流程部署失败！");
		}

		return new ResponseInfo(true, "流程部署成功！");
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 下午2:20:19
	 * @description 获取流程部署信息
	 * @param rows
	 * @param page
	 * @param s_name
	 * @param response
	 * @return
	 */
	@RequestMapping("/deployPage")
	public String deployPage(String rows, String page, String s_name, HttpServletResponse response){
		if (s_name == null) {
			s_name = "";
		}
		PageInfo<Deployment> pageInfo = new PageInfo<>();
		// 填充每页显示数量
		Integer sizePage = Integer.parseInt(rows);
		pageInfo.setPageSize(sizePage);
		// 第几页
		String pageIndex = page;
		if (pageIndex == null || pageIndex == "") {
			pageIndex = "1";
		}
		pageInfo.setPageIndex((Integer.parseInt(pageIndex) - 1) * sizePage);
		// 取得总数量
		long deployCount = repositoryService.createDeploymentQuery().deploymentNameLike("%" + s_name + "%").count();

		List<Deployment> deployList = repositoryService.createDeploymentQuery()// 创建流程查询实例
				.orderByDeploymenTime().desc() // 降序
				.deploymentNameLike("%" + s_name + "%") // 根据Name模糊查询
				.listPage(pageInfo.getPageIndex(), pageInfo.getPageSize());

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "resources" });
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd hh:mm:ss"));
		JSONObject result = new JSONObject();
		JSONArray jsonArray = JSONArray.fromObject(deployList, jsonConfig);
		result.put("rows", jsonArray);
		result.put("total", deployCount);
		try {
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			System.err.println("获取信息失败");
		}
		return null;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月21日 下午2:42:21
	 * @description 删除流程部署
	 * @param response
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delDeploy")
	public String delDeploy(HttpServletResponse response,String ids){
		//拆分字符串
		String[] idsStr=ids.split(",");
		for(String str:idsStr){
			repositoryService.deleteDeployment(str, true);
		}
		JSONObject result=new JSONObject();
		result.put("success", true);
		try {
			ResponseUtils.write(response, result);
		} catch (Exception e) {
			System.err.println("删除失败");
		}
		return null;
	}
}
