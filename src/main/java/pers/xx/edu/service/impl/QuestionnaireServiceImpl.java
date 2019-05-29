package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.QuestionnaireDao;
import pers.xx.edu.entity.Questionnaire;
import pers.xx.edu.service.QuestionnaireService;

/**
 * @author XieXing
 * @createDate 2019年5月28日 下午2:49:40
 * @description 问卷调查实现类
 */
@Transactional
@Service("questionnaireService")
public class QuestionnaireServiceImpl extends BaseServiceImpl<Questionnaire> implements QuestionnaireService{
	@Resource(name = "questionnaireDao")
	@Override
	protected void setBaseDao(BaseDao<Questionnaire> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected QuestionnaireDao getBaseDao() {
		return (QuestionnaireDao) super.getBaseDao();
	}

	@Override
	public Questionnaire getByStuId(Integer stuId) {
		return this.getBaseDao().getByStuId(stuId);
	}

}
