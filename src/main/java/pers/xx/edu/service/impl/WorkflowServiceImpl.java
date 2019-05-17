package pers.xx.edu.service.impl;

import java.io.File;

import javax.transaction.Transactional;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.xx.edu.service.WorkflowService;
import pers.xx.edu.utils.ResponseInfo;

/**
 * @author XieXing
 * @createDate 2019年4月23日 上午10:50:44
 * @description 工作流实现类
 */
@Transactional
@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {

	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;
	
	@Override
	public ResponseInfo saveNewDeploye(File file, String filename) {
		return new ResponseInfo(true,"提交成功！");
	}

}
