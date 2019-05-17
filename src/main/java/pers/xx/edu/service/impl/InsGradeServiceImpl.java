package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.InsGradeDao;
import pers.xx.edu.entity.InsGrade;
import pers.xx.edu.service.InsGradeService;

/**
 * @author XieXing
 * @createDate 2019年5月6日 上午11:24:22
 * @description 导员-年级DAO
 */
@Transactional
@Service("insGradeService")
public class InsGradeServiceImpl extends BaseServiceImpl<InsGrade> implements InsGradeService{
	@Resource(name = "insGradeDao")
	@Override
	protected void setBaseDao(BaseDao<InsGrade> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected InsGradeDao getBaseDao() {
		return (InsGradeDao) super.getBaseDao();
	}
}
