package pers.xx.edu.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.MyCityDao;
import pers.xx.edu.entity.MyCity;
import pers.xx.edu.service.MyCityService;

/**
 * @author XieXing
 * @createDate 2019年5月5日 上午9:30:59
 * @description 市实体接口实现类
 */
@Transactional
@Service("myCityService")
public class MyCityServiceImpl extends BaseServiceImpl<MyCity> implements MyCityService{
	@Resource(name = "myCityDao")
	@Override
	protected void setBaseDao(BaseDao<MyCity> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected MyCityDao getBaseDao() {
		return (MyCityDao) super.getBaseDao();
	}

	@Override
	public List<MyCity> getByPid(Integer pid) {
		return this.getBaseDao().getByPid(pid);
	}
}
