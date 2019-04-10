package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.LeaderDao;
import pers.xx.edu.entity.Leader;
import pers.xx.edu.service.LeaderService;

/**
 * @author XieXing
 * @description 领导接口实现类
 * @create 2019年3月17日 下午6:35:06
 */
@Transactional
@Service("leaderService")
public class leaderServiceImpl extends BaseServiceImpl<Leader> implements LeaderService{

	@Resource(name = "leaderDao")
	@Override
	protected void setBaseDao(BaseDao<Leader> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected LeaderDao getBaseDao() {
		return (LeaderDao) super.getBaseDao();
	}
}
