package pers.xx.edu.service;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import pers.xx.edu.entity.Leader;
import pers.xx.edu.vo.LeaderVo;

/**
 * @author XieXing
 * @description 领导接口
 * @create 2019年3月17日 下午6:31:48
 */
public interface LeaderService extends BaseService<Leader> {
	/**
	 * @author XieXing
	 * @createDate 2019年4月15日 上午9:19:52
	 * @description 领导登陆验证
	 * @param code
	 * @param password
	 * @return
	 */
	Leader login(String code,String password);
	
	/**
	 * @author XieXing
	 * @createDate 2019年4月12日 下午2:32:45
	 * @description 编辑领导信息
	 * @param leaderVo
	 * @param leaderEntranceDate
	 * @param leaderBirth
	 * @param leaderTitle
	 */
	void edit(LeaderVo leaderVo, String leaderEntranceDate, String leaderBirth, Integer leaderTitle,CommonsMultipartFile img,HttpSession session);
}
