package pers.xx.edu.dao;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import pers.xx.edu.entity.Questionnaire;

/**
 * @author XieXing
 * @createDate 2019年5月28日 下午2:47:20
 * @description 问卷调查DAO类
 */
@Repository
public class QuestionnaireDao extends BaseDao<Questionnaire>{

	/**
	 * @author XieXing
	 * @createDate 2019年5月28日 下午3:34:20
	 * @description 根据学生id获取其调查问卷
	 * @param stuId
	 * @return
	 */
	public Questionnaire getByStuId(Integer stuId) {
		String hql = "from Questionnaire where qnStudent = ?";
		Query query = this.getSession().createQuery(hql);
		query.setInteger(0, stuId);
		if (query.list().size()==0)
			return null;
		else
			return (Questionnaire) query.list().get(0);
	}
}
