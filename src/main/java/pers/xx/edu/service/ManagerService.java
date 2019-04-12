package pers.xx.edu.service;

import pers.xx.edu.entity.Manager;
import pers.xx.edu.vo.ManagerVo;

/**
 * @author XieXing
 * @description 管理员接口
 * @create 2019年3月17日 下午6:33:07
 */
public interface ManagerService extends BaseService<Manager>{
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午3:54:11
	 * @description 管理员信息编辑
	 * @param managerVo
	 * @param managerEntranceDate
	 * @param managerBirth
	 */
	void edit(ManagerVo managerVo, String managerEntranceDate, String managerBirth);
}
