package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Manager;
import pers.xx.edu.service.ManagerService;

/**
 * @author XieXing
 * @description 管理员接口实现类
 * @create 2019年3月17日 下午6:35:06
 */
@Transactional
@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService{

	@Resource(name = "managerDao")
	@Override
	protected void setBaseDao(BaseDao<Manager> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected BaseDao<Manager> getBaseDao() {
		return super.getBaseDao();
	}
}
