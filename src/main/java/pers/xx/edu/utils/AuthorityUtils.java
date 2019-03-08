package pers.xx.edu.utils;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import pers.xx.edu.entity.Department;
import pers.xx.edu.entity.User;

/**
 * 权限过滤
 */
public class AuthorityUtils {
	/**
	 * 返回权限判断条件
	 */
	public static Map<String,Object> getAuthority(HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		Department department = user.getDepartment();
		String dcWarehoue = department.getName();
		Map<String,Object> map=new HashMap<String,Object>();
		if(!"总部".equals(dcWarehoue)){
		     map.put("dcWarehouse = ?", dcWarehoue);
		}
		return map;
	}

	public static Map<String, Object> getUrl(HttpServletRequest request, Model model,String departmentId){
		Map<String, Object> map=new HashMap<>();
		String url="";
		User user = (User) request.getSession().getAttribute("user");
		String name = user.getDepartment().getName();
		switch (departmentId) {
			case "1":
				if (name.equals("总部"))
					url = "achievement/applyList";
				break; //企管部
			case "2":
				if (name.equals("财务部")||name.equals("总部"))
					url = "achievement/financial";
				break; //财务部
			case "3":
				if (name.equals("企管部")||name.equals("总部"))
					url = "achievement/management";
				break; //企管部
			case "4":
				if(name.equals("总部") || name.equals("质安部"))
					url = "achievement/qualityHome";
			case "5":
				if(name.equals("总部") || name.equals("行政部"))
					url = "achievement/qualityHome";
				break; //行政部
		}
		map.put("url",url);
		map.put("model",model);
		return map;
	}

	public static Map<String, Object> getUrlList(HttpServletRequest request, Model model,String departmentId){
		Map<String, Object> map=new HashMap<>();
		String url="";
		User user = (User) request.getSession().getAttribute("user");
		String name = user.getDepartment().getName();
		switch (departmentId) {
			case "1":
				if (name.equals("总部"))
					url = "achievement/list";
				break; //总部
			case "2":
				if (name.equals("财务部")||name.equals("总部"))
					url = "achievement/financialHome";
				break; //财务部
			case "3":
				if (name.equals("企管部")||name.equals("总部"))
					url = "achievement/managementHome";
				break; //企管部
			case "4":
				if(name.equals("总部") || name.equals("质安部"))
					url = "achievement/qualityHome";
				break; //质安部
			case "5":
				if(name.equals("总部") || name.equals("行政部"))
					url = "achievement/qualityHome";
				break; //行政部
				
		}
		model.addAttribute("authority", name);
		map.put("url",url);
		map.put("model",model);
		return map;
	}
	
	

}
