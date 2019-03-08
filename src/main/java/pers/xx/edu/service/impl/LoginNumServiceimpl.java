package pers.xx.edu.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.LoginNumDao;
import pers.xx.edu.entity.LoginNum;
import pers.xx.edu.service.LoginNumService;

@Transactional
@Service("loginNumService")
public class LoginNumServiceimpl extends BaseServiceImpl<LoginNum> implements LoginNumService {
	private LoginNumDao loginNumDao;

	@Resource(name = "loginNumDao")
	protected void setBaseDao(BaseDao<LoginNum> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected LoginNumDao getBaseDao() {
		return (LoginNumDao) super.getBaseDao();
	}

	@Override
	public List<?> find(String numberorname, Date begin, Date end) {
		return loginNumDao.find(numberorname, begin, end);
	}

}
