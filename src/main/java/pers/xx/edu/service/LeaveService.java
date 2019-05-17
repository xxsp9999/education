package pers.xx.edu.service;

import pers.xx.edu.entity.Leave;

/**
 * @author XieXing
 * @createDate 2019年4月18日 上午10:10:16
 * @description 请假接口
 */
public interface LeaveService extends BaseService<Leave>{
	/**
	 * @author XieXing
	 * @createDate 2019年4月24日 下午2:29:05
	 * @description 根据流程实例id获取请假信息
	 * @param processInstanceId
	 * @return
	 */
	Leave getByProcessInstanceId(String processInstanceId);

}
