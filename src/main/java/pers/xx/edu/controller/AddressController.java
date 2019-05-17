package pers.xx.edu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pers.xx.edu.entity.MyCity;
import pers.xx.edu.entity.MyCounty;
import pers.xx.edu.entity.MyProvince;
import pers.xx.edu.service.MyCityService;
import pers.xx.edu.service.MyCountyService;
import pers.xx.edu.service.MyProvinceService;

/**
 * @author XieXing
 * @createDate 2019年5月8日 下午6:12:39
 * @description 地址Controller
 */
@Controller
@RequestMapping("/address")
public class AddressController {
	
	@Autowired
	private MyProvinceService myProvinceService;
	
	@Autowired
	private MyCountyService myCountyService;
	
	@Autowired
	private MyCityService myCityService;
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月8日 下午6:17:43
	 * @description 获取省份
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getProvinces")
	public List<MyProvince> getProvices() {
		List<MyProvince> myProvinces = myProvinceService.getAll();
		return myProvinces;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月8日 下午6:20:33
	 * @description 获取市
	 * @param pid
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCities")
	public List<MyCity> getCities(Integer pid) {
		List<MyCity> myCities = myCityService.getByPid(pid);
		return myCities;
	}
	
	/**
	 * @author XieXing
	 * @createDate 2019年5月8日 下午6:19:04
	 * @description 根据市获取县(区)
	 * @param cityId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/getCounties")
	public List<MyCounty> getCounties(Integer cid) {
		List<MyCounty> myCounties = myCountyService.getByCityId(cid);
		return myCounties;
	}

}
