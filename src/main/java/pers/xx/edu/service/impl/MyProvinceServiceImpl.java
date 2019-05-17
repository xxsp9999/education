package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.MyProvinceDao;
import pers.xx.edu.entity.MyProvince;
import pers.xx.edu.service.MyProvinceService;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:28:43
 * @description 省份实体实现类
 */
@Transactional
@Service("myProvinceService")
public class MyProvinceServiceImpl extends BaseServiceImpl<MyProvince> implements MyProvinceService {

	@Resource(name = "myProvinceDao")
	@Override
	protected void setBaseDao(BaseDao<MyProvince> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected MyProvinceDao getBaseDao() {
		return (MyProvinceDao) super.getBaseDao();
	}

}
