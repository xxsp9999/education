package pers.xx.edu.service;

import java.util.List;
import java.util.Map;

import pers.xx.edu.utils.Page;

/**
 * 
 * @description 所有Service的父类类 
 * @author 白贵才
 * @create 2017-9-13下午2:57:30
 *
 */
public interface BaseService<T> {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(T entity);
	
	/**
	 * 更新
	 * @param entity
	 */
	public void update(T entity);
	
	/**
	 * 保存或更新，根据实体的Id是不是为0来判断
	 * @param entity
	 */
	public void saveOrUpdate(T entity);
	
	/**
	 * 删除
	 * @param entity
	 */
	public void delete(T entity);
	
	/**
	 * 通过Id删除
	 * @param table
	 * @param id
	 */
	public void deleteById(String table,int id);
	
	/**
	 * 通过id删除
	 * @param id
	 */
	public void deleteById(int id);
	
	/**
	 * 通过id批量删除
	 * @param ids
	 */
	public void deleteByIds(Integer[] ids);
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<T> getAll();
	
	/**
	 * 获取分页数据
	 * @param params page、rows、其他搜索参数
	 * @param orderOrGroupBy 排序和分组
	 * @return
	 */
	public Page<T> getPageList(Map<String,Object> params,Map<String,String> orderOrGroupBy);
	
	/**
	 * 模糊获取分页数据
	 * @param params page、rows、其他搜索参数
	 * @param orderOrGroupBy 排序和分组
	 * @return
	 */
	public Page<T> getPageListByMohu(Map<String,Object> params,Map<String,String> orderOrGroupBy);
	
	
	/**
	 * 获取数据
	 * @param params page、rows、其他搜索参数
	 * @param orderOrGroupBy 排序和分组
	 * @return
	 */
	public List<T> getList(Map<String,Object> params,Map<String,String> orderOrGroupBy);
	
	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	public T getById(int id);
	
	/**
	 * 根据其他唯一的参数获取实体
	 * @param params
	 * @return
	 */
	public T getByParams(Map<String,Object> params);
	
	/**
	 * 根据id集合批量获取实体
	 * @param ids
	 * @return
	 */
	public List<T> getByIds(Integer[] ids);
	
	/**
	 * 
	 * 获取搜索的分页数据
	 * @param params page、rows、其他搜索参数
	 * @param orderOrGroupBy 排序和分组
	 * @return
	 * @return
	 */
	public Page<T> getLikePageList(Map<String,Object> params,Map<String,String> orderOrGroupBy);
	
	/**
	 * 
	 * 获取不分页List数据
	 * @param params page、rows、其他搜索参数
	 * @param orderOrGroupBy 排序和分组
	 * @return
	 * @return
	 */
	public List<T> getLikeList(Map<String,Object> params,Map<String,String> orderOrGroupBy);
	
}
