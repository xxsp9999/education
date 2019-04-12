package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.LeaderTitleDao;
import pers.xx.edu.entity.LeaderTitle;
import pers.xx.edu.service.LeaderTitleService;

/**
 * @author XieXing
 * @createDate 2019年4月12日 下午2:20:07
 * @description
 */
@Transactional
@Service("leaderTitleService")
public class LeaderTitleServiceImpl extends BaseServiceImpl<LeaderTitle> implements LeaderTitleService {
	@Resource(name = "leaderTitleDao")
	@Override
	protected void setBaseDao(BaseDao<LeaderTitle> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected LeaderTitleDao getBaseDao() {
		return (LeaderTitleDao) super.getBaseDao();
	}
}
