package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.entity.Task;
import pers.xx.edu.service.TaskService;

/**
 * @author xiexing
 * @description
 * @create 2018年10月22日 下午6:16:11
 */
@Service
@Transactional
public class TaskServiceImpl extends BaseServiceImpl<Task> implements TaskService {
	
	@Resource(name = "taskDao")
	@Override
	protected void setBaseDao(BaseDao<Task> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected BaseDao<Task> getBaseDao() {
		return super.getBaseDao();
	}
}
