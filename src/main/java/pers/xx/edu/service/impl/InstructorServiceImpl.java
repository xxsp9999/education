package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.InstructorDao;
import pers.xx.edu.entity.Instructor;
import pers.xx.edu.service.InstructorService;

/**
 * @author XieXing
 * @description 辅导员实现类
 * @create 2019年3月17日 下午6:44:50
 */
@Transactional
@Service
public class InstructorServiceImpl extends BaseServiceImpl<Instructor> implements InstructorService{
	@Resource(name = "instructorDao")
	@Override
	protected void setBaseDao(BaseDao<Instructor> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected InstructorDao getBaseDao() {
		return (InstructorDao) super.getBaseDao();
	}
}
