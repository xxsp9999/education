package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.LeaveDao;
import pers.xx.edu.entity.Leave;
import pers.xx.edu.service.LeaveService;

/**
 * @author XieXing
 * @createDate 2019年4月18日 上午10:11:01
 * @description 请假接口实现类
 */
@Transactional
@Service("leaveService")
public class LeaveServiceImpl extends BaseServiceImpl<Leave> implements LeaveService{

	@Resource(name = "leaveDao")
	@Override
	protected void setBaseDao(BaseDao<Leave> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected LeaveDao getBaseDao() {
		return (LeaveDao) super.getBaseDao();
	}

	@Override
	public Leave getByProcessInstanceId(String processInstanceId) {
		return this.getBaseDao().getByProcessInstanceId(processInstanceId);
	}
}
