package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Params;
import pers.xx.edu.service.ParamsService;

@Service("paramsService")
@Transactional
public class ParamsServiceImpl extends BaseServiceImpl<Params> implements ParamsService{

	@Resource(name = "paramsDao")
	@Override
	protected void setBaseDao(BaseDao<Params> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected BaseDao<Params> getBaseDao() {
		return super.getBaseDao();
	}


}
