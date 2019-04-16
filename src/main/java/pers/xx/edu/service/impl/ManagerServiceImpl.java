package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.ManagerDao;
import pers.xx.edu.entity.Manager;
import pers.xx.edu.service.ManagerService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.ManagerVo;

/**
 * @author XieXing
 * @description 管理员接口实现类
 * @create 2019年3月17日 下午6:35:06
 */
@Transactional
@Service("managerService")
public class ManagerServiceImpl extends BaseServiceImpl<Manager> implements ManagerService {

	@Resource(name = "managerDao")
	@Override
	protected void setBaseDao(BaseDao<Manager> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected ManagerDao getBaseDao() {
		return (ManagerDao) super.getBaseDao();
	}

	@Override
	public void edit(ManagerVo managerVo, String managerEntranceDate, String managerBirth) {
		Manager manager = null;
		Integer id = managerVo.getId();
		if (id != null) {
			manager = getById(id);
			manager.setManagerPassword(StringUtils.StringToMd5("123456"));
		} else {
			manager = new Manager();
		}
		BeanUtils.copyProperties(managerVo, manager);
		try {
			Date enDate = DateTimeUtils.deal(managerEntranceDate);
			manager.setManagerEntranceDate(enDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		try {
			Date birthDate = DateTimeUtils.deal(managerBirth);
			manager.setManagerBirth(birthDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		saveOrUpdate(manager);
	}

	@Override
	public Manager login(String code, String password) {
		return this.getBaseDao().login(code, password);
	}

}
