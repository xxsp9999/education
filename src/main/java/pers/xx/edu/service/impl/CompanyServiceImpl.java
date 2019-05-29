package pers.xx.edu.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.CompanyDao;
import pers.xx.edu.entity.Company;
import pers.xx.edu.service.CompanyService;

/**
 * @author XieXing
 * @createDate 2019年5月11日 下午4:20:14
 * @description 公司类接口实现类
 */
@Transactional
@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService{
	@Resource(name = "companyDao")
	@Override
	protected void setBaseDao(BaseDao<Company> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected CompanyDao getBaseDao() {
		return (CompanyDao) super.getBaseDao();
	}

	@Override
	public Company login(String code, String password) {
		return this.getBaseDao().login(code, password);
	}
}
