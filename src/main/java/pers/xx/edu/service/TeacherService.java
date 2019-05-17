package pers.xx.edu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Teacher;
import pers.xx.edu.vo.TeacherVo;

/**
 * @author XieXing
 * @description
 * @create 2019年3月17日 下午6:29:54
 */
public interface TeacherService extends BaseService<Teacher>{

	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:17:27
	 * @description 教师登陆验证
	 * @param code
	 * @param password
	 * @return
	 */
	Teacher login(String code,String password);
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 上午10:08:29
	 * @description 编辑教师信息接口
	 * @param teacherVo
	 * @param teaEntranceDate
	 * @param teaBirth
	 * @param teaTitleId
	 */
	void edit(TeacherVo teacherVo, String teaEntranceDate, String teaBirth, Integer teaTitleId,Integer teaAdTitleId,Integer faculty,Integer major,CommonsMultipartFile img,HttpSession session);

	/**
	 * @author XieXing
	 * @createDate 2019年4月23日 下午5:15:31
	 * @description 获取审核教师
	 * @param params
	 * @return
	 */
	Teacher getCheckTeacher(Map<String, Object> params);
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 上午10:52:46
	 * @description 根据学院获取老师
	 * @param facId
	 * @return
	 */
	public List<Teacher> getByFacId(Integer facId);
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 上午10:53:04
	 * @description 根据专业获取老师
	 * @param majId
	 * @return
	 */
	public List<Teacher> getByMajId(Integer majId);
}
