package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.GradeDao;
import pers.xx.edu.entity.Grade;
import pers.xx.edu.service.GradeService;

/**
 * @author XieXing
 * @createDate 2019年5月2日 上午10:27:56
 * @description 
 */
@Transactional
@Service("gradeService")
public class GradeServiceImpl extends BaseServiceImpl<Grade> implements GradeService{
	@Resource(name = "gradeDao")
	@Override
	protected void setBaseDao(BaseDao<Grade> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected GradeDao getBaseDao() {
		return (GradeDao) super.getBaseDao();
	}
}
