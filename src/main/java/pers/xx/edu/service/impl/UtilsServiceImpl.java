package pers.xx.edu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.UtilsDao;
import pers.xx.edu.service.UtilsService;

@Transactional
@Service("utilsService")
public class UtilsServiceImpl implements UtilsService {

	@Autowired
	private UtilsDao utilsDao;
	
	@Override
	public boolean isExistUUID(String entity, String column, String value) {
		return this.utilsDao.isExistUUID(entity,column,value);
	}

}
