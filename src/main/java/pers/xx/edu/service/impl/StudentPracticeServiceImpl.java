package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.StudentPracticeDao;
import pers.xx.edu.entity.StudentPractice;
import pers.xx.edu.service.StudentPracticeService;

/**
 * @author XieXing
 * @createDate 2019年5月25日 上午9:45:08
 * @description 学生活动接口实现类
 */
@Service("studentPracticeService")
@Transactional
public class StudentPracticeServiceImpl extends BaseServiceImpl<StudentPractice> implements StudentPracticeService{
	@Resource(name = "studentPracticeDao")
	@Override
	protected void setBaseDao(BaseDao<StudentPractice> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected StudentPracticeDao getBaseDao() {
		return (StudentPracticeDao) super.getBaseDao();
	}
}
