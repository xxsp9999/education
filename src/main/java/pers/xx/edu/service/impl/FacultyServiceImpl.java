package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.FacultyDao;
import pers.xx.edu.entity.Faculty;
import pers.xx.edu.service.FacultyService;

/**
 * @author XieXing
 * @createDate 2019年4月9日 下午3:28:33
 * @description 学院接口实现类
 */
@Transactional()
@Service("facultyService")
public class FacultyServiceImpl extends BaseServiceImpl<Faculty> implements FacultyService{
	@Resource(name = "facultyDao")
	@Override
	protected void setBaseDao(BaseDao<Faculty> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected FacultyDao getBaseDao() {
		return (FacultyDao) super.getBaseDao();
	}

	@Override
	public Faculty getFacultyByfacNumber(String facNumber) {
		return getBaseDao().getFacultyByfacNumber(facNumber);
	}
}
