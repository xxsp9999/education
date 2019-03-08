package pers.xx.edu.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.MessageDao;
import pers.xx.edu.entity.MessageTran;
import pers.xx.edu.service.BaseService;

@Transactional
@Service("messageService")
public class MessageServiceImpl extends BaseServiceImpl<MessageTran> implements BaseService<MessageTran> {

	@Resource(name = "messageDao")
	@Override
	protected void setBaseDao(BaseDao<MessageTran> baseDao) {
		super.setBaseDao(baseDao);
	}

	@Override
	protected MessageDao getBaseDao() {
		return (MessageDao)super.getBaseDao();
	}


}
