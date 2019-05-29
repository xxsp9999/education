package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.CoursewareDao;
import pers.xx.edu.entity.Courseware;
import pers.xx.edu.service.CoursewareService;

/**
 * @author XieXing
 * @createDate 2019年5月17日 下午5:04:22
 * @description 课件接口实现类
 */
@Transactional
@Service("coursewareService")
public class CoursewareServiceImpl extends BaseServiceImpl<Courseware> implements CoursewareService{
	@Resource(name = "coursewareDao")
	@Override
	protected void setBaseDao(BaseDao<Courseware> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected CoursewareDao getBaseDao() {
		return (CoursewareDao) super.getBaseDao();
	}
}
