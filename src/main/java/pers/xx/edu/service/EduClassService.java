package pers.xx.edu.service;

import java.util.List;

import pers.xx.edu.entity.EduClass;
import pers.xx.edu.vo.EduClassVo;

/**
 * @author XieXing
 * @createDate 2019年4月19日 下午4:15:08
 * @description 班级接口
 */
public interface EduClassService extends BaseService<EduClass>{
	/**
	 * @author XieXing
	 * @createDate 2019年5月2日 上午11:37:40
	 * @description 编辑班级信息
	 * @param eduClassVo
	 * @param gradeId
	 * @param teaId
	 */
	void edit(EduClassVo eduClassVo,Integer gradeId,Integer teaId);
	
	List<EduClass> getByLikeContent(String str);
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 上午11:09:49
	 * @description 跟据学院id获取班级
	 * @param facId
	 * @return
	 */
	List<EduClass> getByFacId(Integer facId);

	/**
	 * @author XieXing
	 * @createDate 2019年5月6日 上午11:10:12
	 * @description 根据专业id获取班级
	 * @param majId
	 * @return
	 */
	List<EduClass> getByMajId(Integer majId);
}
