package pers.xx.edu.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pers.xx.edu.dao.BaseDao;
import pers.xx.edu.service.BaseService;
import pers.xx.edu.utils.Page;

@Service("baseService")
@Transactional
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDao<T> baseDao;

	/**
	 * 子类必须重写这个方法, 并且注入对应的BaseDao (OrderService需注入BaseDao<Order>)
	 * @param baseDao
	 */
	protected void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}

	protected BaseDao<T> getBaseDao() {
		return baseDao;
	}

	@Override
	public void save(T entity) {
		getBaseDao().save(entity);
	}

	@Override
	public void update(T entity) {
		getBaseDao().update(entity);
	}

	@Override
	public void saveOrUpdate(T entity) {
		getBaseDao().saveOrUpdate(entity);
	}

	@Override
	public void delete(T entity) {
		getBaseDao().delete(entity);
	}

	@Override
	public void deleteById(String table,int id){
		getBaseDao().deleteById(table,id);
	}

	@Override
	public void deleteById(int id) {
		getBaseDao().deleteById(id);
	}

	@Override
	public void deleteByIds(Integer[] ids) {
		getBaseDao().deleteByIds(ids);
	}

	@Override
	public Page<T> getPageList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		return this.getBaseDao().getPageList(params, orderOrGroupBy);
	}

	@Override
	public Page<T> getPageListByMohu(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		return this.getBaseDao().getPageListByMohu(params, orderOrGroupBy);
	}

	@Override
	public List<T> getList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		return this.getBaseDao().getList(params, orderOrGroupBy);
	}

	@Override
	public T getById(int id) {
		return this.getBaseDao().getById(id);
	}

	@Override
	public T getByParams(Map<String, Object> params) {
		return this.getBaseDao().getByParams(params);
	}

	@Override
	public List<T> getByIds(Integer[] ids) {
		return this.getBaseDao().getByIds(ids);
	}

	@Override
	public Page<T> getLikePageList(Map<String,Object> params,Map<String,String> orderOrGroupBy){
		return this.getBaseDao().getLikePageList(params, orderOrGroupBy);
	}

	@Override
	public List<T> getLikeList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		return this.getBaseDao().getLikeList(params, orderOrGroupBy);
	}

	@Override
	public List<T> getAll() {
		return this.getBaseDao().getAll();
	}

	@Override
	public Page<T> getPageListNew(Map<String, Object> params, Map<String, String> orderOrGroupBy) {
		return this.getBaseDao().getPageListNew(params, orderOrGroupBy);
	}

	@Override
	public int batchSave(List<T> list) {
		return this.getBaseDao().batchSave(list);
	}

	@Override
	public void saveByUnit(T entity) {
		this.getBaseDao().saveByUnit(entity);
	}

	@Override
	public Integer getCount(Map<String, Object> params) {
		return this.getBaseDao().getCount(params);
	}
}
