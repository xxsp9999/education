package pers.xx.edu.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pers.xx.edu.entity.Module;
import pers.xx.edu.entity.ModuleRole;
import pers.xx.edu.entity.User;
import pers.xx.edu.service.ModuleRoleService;
import pers.xx.edu.service.ModuleService;
import pers.xx.edu.utils.ImageUtils;
import pers.xx.edu.utils.JsonUtils;
import pers.xx.edu.utils.Page;
import pers.xx.edu.vo.ModuleVo;

/**
 * @author XieXing
 * @description 系统路径跳转
 * @create 2019年2月25日 上午10:37:50
 */
@Controller
@RequestMapping("/system")
public class SystemController {
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
	private ModuleRoleService moduleRoleService;
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 获取验证码
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/code")
	public void getCode(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Object[] objs = ImageUtils.createImage();
		request.getSession().removeAttribute("imgCode");
		request.getSession().setAttribute("imgCode", objs[0]);
		// TODO 输出生成的验证码字符串
		BufferedImage img = (BufferedImage) objs[1];
		response.setContentType("image/png");
		OutputStream os = response.getOutputStream();
		ImageIO.write(img, "png", os);
		os.close();
	}
	
	/**
	 * @author XieXing
	 * @create 2019年2月25日
	 * @description 跳转至登录
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "system/login";
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月16日 下午3:11:11
	 * @description
	 * @param request
	 * @return
	 */
	@RequestMapping("/module/toList")
	public String toModule(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("level = ?", "0");
		List<Module> moduleList = moduleService.getList(params, null);
		JSONArray json = new JSONArray();//格式化父级菜单名称
		StringBuffer select = new StringBuffer();//父级菜单select
		if(moduleList != null && moduleList.size() > 0){
			for (Module modu : moduleList) {
				JSONObject obj = new JSONObject();
				obj.put("id", modu.getId());
				obj.put("name", modu.getName());
				json.add(obj);
				select.append(modu.getId()+":\'"+modu.getName()+"\',");
			}
		}
		request.setAttribute("moduleJSON", json);
		request.setAttribute("select", "{value:{0:'无',"+select.toString().substring(0, select.toString().length()-1)+"}}");

		return "/settings/module/list";
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月16日 下午3:15:48
	 * @description 获取所有模块
	 * @param page
	 * @param rows
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/module/getList")
	public void ModuleList(@RequestParam(value="page",defaultValue="0") int page,
			@RequestParam(value="rows") int rows,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("page", page);
		params.put("rows", rows);
		//params.put("level != ?", "0");
		Page<Module> pageBean = moduleService.getPageList(params, null);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");
		response.getWriter().print(pageBean.toJqGridString());
		response.getWriter().flush();
		response.getWriter().close();
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月16日 下午3:18:12
	 * @description 模块增删改
	 * @param id
	 * @param parentModuleId
	 * @param oper
	 * @param moduleVo
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/module/edit",produces="application/json;charset=utf-8")
	public String toDepartmentEdit(@RequestParam(value="id",required=false) String id, @RequestParam(value="parentModuleId",required=false) String parentModuleId,String oper, ModuleVo moduleVo, HttpServletRequest request){
		StringBuffer msg1 = new StringBuffer();
		StringBuffer msg2 = new StringBuffer();
		User currentUser = (User) request.getSession().getAttribute("user");
		Module module = new Module();
		if(id == null || id.equals("") || id.equals("_empty")){
			//增加
			BeanUtils.copyProperties(moduleVo, module);
			module.setCreateUser(currentUser);
			module.setCreateDate(new Date());
			if(parentModuleId != null && !parentModuleId.equals("") && !parentModuleId.equals("0")){
				Module parent = moduleService.getById(Integer.valueOf(parentModuleId));

				module.setParent(parent);
				module.setLevel("1");
			}else{
				module.setLevel("0");
			}
			moduleService.save(module);
		}else{
			//更新或删除
			if(oper != null && !oper.equals("")){
				if(oper.equals("del")){
					//删除
					String ids[] = id.split(",");
					Map<String,Object> params = new HashMap<String,Object>();
					if(ids!=null && ids.length>0){
						for(String i:ids){

							params.put("module.id = ?", Integer.valueOf(i));
							List<ModuleRole> list = moduleRoleService.getList(params, null);

							params.clear();
							params.put("parent.id = ?", Integer.valueOf(i));
							List<Module> childList = moduleService.getList(params, null);
							params.clear();
							//有对应关系不能删除
							if((list != null && list.size() > 0) || (childList != null && childList.size() > 0)){
								if(list != null && list.size() > 0){
									msg1.append(list.get(0).getModule().getName()+",");
								}
								if(childList != null && childList.size() > 0){
									msg2.append(childList.get(0).getName()+",");
								}
							}else{
								module.setId(Integer.valueOf(i));
								moduleService.deleteById("module",module.getId());
							}

						}
					}
				}else{
					//更新
					module = moduleService.getById(Integer.valueOf(id));
					BeanUtils.copyProperties(moduleVo, module);
					module.setUpdateDate(new Date());
					module.setUpdateUser(currentUser);
					if(parentModuleId != null && !parentModuleId.equals("")){
						if(!parentModuleId.equals("0")){
							Module parent = moduleService.getById(Integer.valueOf(parentModuleId));
							module.setParent(parent);
							module.setLevel("1");
						}else{
							module.setParent(null);
							module.setLevel("0");
						}
					}
					moduleService.update(module);
				}
			}
		}
		if(!"".equals(msg1.toString()) || !"".equals(msg2.toString())){
			StringBuffer returnMsg = new StringBuffer();
			if(!"".equals(msg1.toString())){
				msg1.deleteCharAt(msg1.length()-1);
				returnMsg.append("部分删除失败!【"+msg1.toString()+"】模块分配了角色,需要取消对应角色的模块,才能删除;");
			}
			if(!"".equals(msg2.toString())){
				msg2.deleteCharAt(msg2.length()-1);
				returnMsg.append("部分删除失败!【"+msg2.toString()+"】模块有子模块,需要删除子模块,才能删除");
			}

			return "{\"status\":\"success\",\"msg\":\""+returnMsg.toString()+"\"}";
		}else{
			return "{\"status\":\"success\"}";
		}
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月19日 上午10:45:05
	 * @description 获取父级菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getParentModule",produces = "application/json;charset=utf-8")
	@ResponseBody
	public String getParentModule(HttpServletRequest request){
		Map<String,Object> params = new HashMap<String,Object>();
		List<Module> list = moduleService.getList(params, null);
		List<String> Pmodule=new ArrayList<String>();
		for(int i=0;i<list.size();i++ ){
			if(list.get(i).getLevel().equals("0"))
				Pmodule.add(list.get(i).getId()+":"+list.get(i).getName());
		}
		return JsonUtils.toStandardJson(Pmodule) ;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月25日 下午3:55:06
	 * @description 图片加载
	 * @param path
	 * @param request
	 * @param response
	 */
	@RequestMapping("/loadImgOnline")
	public void loadImg(String path, HttpServletRequest request,
			HttpServletResponse response) {
		// 读取本地图片输入流
		try {
			FileInputStream inputStream = new FileInputStream(path);
			int i = inputStream.available();
			// byte数组用于存放图片字节数据
			byte[] buff = new byte[i];
			inputStream.read(buff);
			// 记得关闭输入流
			inputStream.close();
			// 设置发送到客户端的响应内容类型
			response.setContentType("image/*");
			OutputStream out = response.getOutputStream();
			out.write(buff);
			// 关闭响应输出流
			out.close();
		} catch (Exception e) {
			System.err.println("图片不存在.");
		}

	}
}
