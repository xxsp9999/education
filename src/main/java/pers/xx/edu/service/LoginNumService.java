package pers.xx.edu.service;

import java.util.Date;
import java.util.List;

import pers.xx.edu.entity.LoginNum;

public interface LoginNumService extends BaseService<LoginNum>{
	
	List find(String numberorname,Date begin,Date end);
	
}
