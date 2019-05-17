package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.MyCountyDao;
import pers.xx.edu.entity.MyCounty;
import pers.xx.edu.service.MyCountyService;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:33:22
 * @description 
 */
@Transactional
@Service("myCountyService")
public class MyCountServiceImpl extends BaseServiceImpl<MyCounty> implements MyCountyService{
	@Resource(name = "myCountyDao")
	@Override
	protected void setBaseDao(BaseDao<MyCounty> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected MyCountyDao getBaseDao() {
		return (MyCountyDao) super.getBaseDao();
	}

	@Override
	public List<MyCounty> getByCityId(Integer cid) {
		return this.getBaseDao().getByCityId(cid);
	}
}
