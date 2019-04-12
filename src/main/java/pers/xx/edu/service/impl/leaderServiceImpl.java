package pers.xx.edu.service.impl;

import java.text.ParseException;
import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.dao.LeaderDao;
import pers.xx.edu.entity.Leader;
import pers.xx.edu.entity.LeaderTitle;
import pers.xx.edu.service.LeaderService;
import pers.xx.edu.service.LeaderTitleService;
import pers.xx.edu.utils.DateTimeUtils;
import pers.xx.edu.utils.StringUtils;
import pers.xx.edu.vo.LeaderVo;

/**
 * @author XieXing
 * @description 领导接口实现类
 * @create 2019年3月17日 下午6:35:06
 */
@Transactional
@Service("leaderService")
public class leaderServiceImpl extends BaseServiceImpl<Leader> implements LeaderService{
	@Autowired
	private LeaderTitleService leaderTitleService;
	@Resource(name = "leaderDao")
	@Override
	protected void setBaseDao(BaseDao<Leader> baseDao) {
		super.setBaseDao(baseDao);
	}

	protected LeaderDao getBaseDao() {
		return (LeaderDao) super.getBaseDao();
	}

	@Override
	public void edit(LeaderVo leaderVo, String leaderEntranceDate, String leaderBirth, Integer leaderTitle) {
		Leader leader = null;
		Integer id = leaderVo.getId();
		if (id != null) {
			leader = getById(id);
			leader.setLeaderPassword(StringUtils.StringToMd5("123456"));
		} else {
			leader = new Leader();
		}
		BeanUtils.copyProperties(leaderVo, leader);
		try {
			Date enDate = DateTimeUtils.deal(leaderEntranceDate);
			leader.setLeaderEntranceDate(enDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		try {
			Date birthDate = DateTimeUtils.deal(leaderBirth);
			leader.setLeaderBirth(birthDate);
		} catch (ParseException e) {
			System.err.println("时间格式不正确！");
		}
		if (leaderTitle != null) {
			LeaderTitle leaderTitle2 = leaderTitleService.getById(leaderTitle);
			leader.setLeaderTitle(leaderTitle2);
		}
		saveOrUpdate(leader);
	}
}
