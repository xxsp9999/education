package pers.xx.edu.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Major;

/**
 * @author XieXing
 * @createDate 2019年4月9日 下午5:18:12
 * @description 专业DAO类
 */
@Repository
public class MajorDao extends BaseDao<Major> {

	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午2:07:09
	 * @description 根据专业代码获取专业信息
	 * @param facNumber 专业代码
	 * @return
	 */
	public Major getMajorBymajorNumber(String majorNumber) {
		String hql = "from Major where majorNumber = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, majorNumber);
		List<Major> majors = query.list();
		return majors.size() > 0 ? majors.get(0) : null;
	}

	/**
	 * @author XieXing
	 * @createDate 2019年4月11日 下午2:24:22
	 * @description 根据学院获取专业
	 * @param facId 学院id
	 * @return
	 */
	public List<Major> getMajorsByFacultyId(Integer facId) {
		String hql = "from Major where majorOfFaculty.id = ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, facId);
		List<Major> majors = query.list();
		return majors;
	}
}
