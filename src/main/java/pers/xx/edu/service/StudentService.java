package pers.xx.edu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Student;
import pers.xx.edu.vo.EChartsVo2;
import pers.xx.edu.vo.StudentVo;

/**
 * @author XieXing
 * @description 学生接口
 * @create 2019年3月17日 下午6:28:59
 */
public interface StudentService extends BaseService<Student> {
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:18:05
	 * @description 学生登陆验证
	 * @param code
	 * @param password
	 * @return
	 */
	Student login(String code,String password);
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午4:33:10
	 * @description 编辑学生信息
	 * @param studentVo
	 * @param stuEntranceDate
	 * @param stuBirth
	 * @param facultyId
	 * @param majorId
	 */
	void edit(StudentVo studentVo, String stuEntranceDate, String stuBirth, Integer facultyId, Integer majorId,Integer stuClassId,Integer provinceId,Integer cityId,Integer countyId,CommonsMultipartFile img,HttpSession session);

	/**
	 * @author XieXing
	 * @createDate 2019年4月30日 上午10:44:28
	 * @description 根据条件获取学生数据
	 * @param content
	 * @return
	 */
	Map<String, Object> getStudentAnalyseData(Integer facId,Integer majId,Integer stuClassId);
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月7日 下午9:28:01
	 * @description 获取所有学生所在城市的数量
	 * @return
	 */
	List<EChartsVo2> getStudentMapData();
	
}
