package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.RecruitmentDao;
import pers.xx.edu.entity.Recruitment;
import pers.xx.edu.service.RecruitmentService;

/**
 * @author XieXing
 * @createDate 2019年5月11日 下午4:22:47
 * @description 招聘接口实现类
 */
@Transactional
@Service("recruitmentService")
public class RecruitmentServiceImpl extends BaseServiceImpl<Recruitment> implements RecruitmentService{
	@Resource(name = "recruitmentDao")
	@Override
	protected void setBaseDao(BaseDao<Recruitment> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected RecruitmentDao getBaseDao() {
		return (RecruitmentDao) super.getBaseDao();
	}
}
