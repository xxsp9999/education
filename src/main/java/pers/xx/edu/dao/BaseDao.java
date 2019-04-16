package pers.xx.edu.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import pers.xx.edu.utils.Page;

/**
 *
 * @description 所有Dao的父类
 * @author XieXing
 * @create 2018-9-14上午9:14:19
 * @param <T>
 *
 */
public abstract class BaseDao<T> {


	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * T实体的实际类型
	 */
	public Class<T> clazz;

	/**
	 * T的简单类名(不是全限定名,全限定名：指的就是com.hdo.dao.BaseDao)
	 */
	public String className;

	/**
	 * 日志类
	 */
	public static Logger log;

	//默认的构造方法（在创建子类对象时调用这个方法）
	@SuppressWarnings("unchecked")
	public BaseDao(){
		//获取当前类（子类）的泛型化父类
		ParameterizedType type = (ParameterizedType) getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
		className = clazz.getSimpleName();
		log = Logger.getLogger(clazz);
	}

	public Session getSession(){
		return this.sessionFactory.getCurrentSession();
	}

	/**
	 * 保存
	 * @param entity
	 */
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 更新
	 * @param entity
	 */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 保存或更新(根据实体中的主键id为0还是大于0)
	 * @param entity
	 */
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 删除实体
	 * @param entity
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * 通过id删除
	 * @param entity
	 */
	public void deleteById(String table,int id) {
		getSession().createSQLQuery("DELETE FROM "+ table +" where id ="+id).executeUpdate();
	}

	/**
	 * 根据id删除id
	 * @param id
	 */
	public void deleteById(int id) {
		String hql = "DELETE FROM "+ className +" WHERE id = :id";
		getSession().createQuery(hql).setParameter("id", id).executeUpdate();
	}

	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteByIds(Integer[] ids) {
		String hql = "DELETE FROM "+ className +" WHERE id in (:ids)";
		getSession().createQuery(hql).setParameterList("ids", ids).executeUpdate();
	}

	/**
	 * 查询所有的信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getAll(){
		String hql="from  "+className+"";
		return getSession().createQuery(hql).list();
	}

	/**
	 * 获取分页的数据，不分页page和rows设置为0就行
	 * @param params
	 * @param orderOrGroupBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> getPageList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		StringBuffer sb1 = new StringBuffer("FROM " + className);
		StringBuffer sb2 = new StringBuffer("SELECT COUNT(*) FROM " + className);
		//如果限制条件不为空，就拼接参数
		if(params != null && params.size() != 0 && params.size() > 2){
			sb1.append(" WHERE");
			sb2.append(" WHERE");
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					sb1.append(" " + en.getKey() + " AND");
					sb2.append(" " + en.getKey() + " AND");
				}
			}
			//去除最后一个多余的"AND"字符串
			sb1.delete(sb1.lastIndexOf(" AND"), sb1.length());
			sb2.delete(sb2.lastIndexOf(" AND"), sb2.length());
			//日志输出打印的hql语句
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb1.toString());
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb2.toString());
		}
		if(orderOrGroupBy != null && orderOrGroupBy.size() != 0){
			for(Entry<String,String> en:orderOrGroupBy.entrySet()){
				//orderOrGroupBy中key为hql语句的group by 或者 order by （格式orderOrGroupBy.put("group by","id");）
				//orderOrGroupBy中value为hql语句的列
				//跳过page和rows的拼接，需要单独拼接
				sb1.append(" " + en.getKey() + " "+en.getValue());
				sb2.append(" " + en.getKey() + " "+en.getValue());
			}
		}
		Query query1 = getSession().createQuery(sb1.toString());
		Query query2 = getSession().createQuery(sb2.toString());
		//如果限制条件不为空，设置查询参数
		if(params != null && params.size() != 0){
			//记录用索引找参数的query.setParameter(int arg0, Object arg1);
			int index = 0;
			for(Entry<String,Object> en:params.entrySet()){
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					if(en.getKey().contains("?")){
						//带有?的hql语句（格式：params.put("id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
						query1.setParameter(index, en.getValue());
						query2.setParameter(index, en.getValue());
						index++;
					}else if((en.getKey().toUpperCase()).contains(" IN")){//这个判断有问题，如果数据库字段含in就会跳到这个判断内，从而导致错误
						//带有in的hql语句（格式：params.put("id in (:id)",{2,3});）方法：query.setParameterList(String arg0, Object[] arg1)
						String key = en.getKey();
						query1.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
						query2.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
					}else if(en.getKey().contains("(") && en.getKey().contains(")") && en.getKey().contains(":")){
						/*
						 * // 特殊处理带有OR的模糊查询 (code LIke :searchStr AND cnName LIKE :searchStr
						 * OR shortName LIKE :searchStr) String key = en.getKey().trim(); int
						 * beginIndex = key.indexOf(":") + 1; int endIndex = key.indexOf(" ",
						 * beginIndex);
						 */
						String key = en.getKey().trim();
						int beginIndex = key.indexOf(":") + 1;
						int endIndex = key.indexOf(" ",beginIndex);
						String paramsKey = key.substring(beginIndex,endIndex);
						query1.setParameter(paramsKey, en.getValue());
						query2.setParameter(paramsKey, en.getValue());
					}
				}
			}
		}
		int page = (int) params.get("page");
		int rows = (int) params.get("rows");
		//设置分页，如果page和rows都为-1时，不分页获取所有的
		if(page != -1 && rows != -1){
			//设置第一条数据的其实索引
			query1.setFirstResult((page-1)*rows);
			//设置最多条数据的数量
			query1.setMaxResults(rows);
		}
		List<T> record = query1.list();
		int count = ((Long) query2.uniqueResult()).intValue();

		Page<T> newPage = new Page<T>(page,(count+rows-1)/rows, count, record);
		return newPage;
	}

	/**
	 * @author XieXing
	 * @create 2019年3月5日
	 * @description  获取分页的数据，不分页page和rows设置为0就行,该方法不支持带in的查询
	 * @param params
	 * @param orderOrGroupBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> getPageListNew(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		StringBuffer sb1 = new StringBuffer("FROM " + className);
		StringBuffer sb2 = new StringBuffer("SELECT COUNT(*) FROM " + className);
		//如果限制条件不为空，就拼接参数
		if(params != null && params.size() != 0 && params.size() > 2){
			sb1.append(" WHERE");
			sb2.append(" WHERE");
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					sb1.append(" " + en.getKey() + " AND");
					sb2.append(" " + en.getKey() + " AND");
				}
			}
			//去除最后一个多余的"AND"字符串
			sb1.delete(sb1.lastIndexOf(" AND"), sb1.length());
			sb2.delete(sb2.lastIndexOf(" AND"), sb2.length());
			//日志输出打印的hql语句
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb1.toString());
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb2.toString());
		}
		if(orderOrGroupBy != null && orderOrGroupBy.size() != 0){
			for(Entry<String,String> en:orderOrGroupBy.entrySet()){
				//orderOrGroupBy中key为hql语句的group by 或者 order by （格式orderOrGroupBy.put("group by","id");）
				//orderOrGroupBy中value为hql语句的列
				//跳过page和rows的拼接，需要单独拼接
				sb1.append(" " + en.getKey() + " "+en.getValue());
				sb2.append(" " + en.getKey() + " "+en.getValue());
			}
		}
		Query query1 = getSession().createQuery(sb1.toString());
		Query query2 = getSession().createQuery(sb2.toString());
		//如果限制条件不为空，设置查询参数
		if(params != null && params.size() != 0){
			//记录用索引找参数的query.setParameter(int arg0, Object arg1);
			int index = 0;
			for(Entry<String,Object> en:params.entrySet()){
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					if(en.getKey().contains("?")){
						//带有?的hql语句（格式：params.put("id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
						query1.setParameter(index, en.getValue());
						query2.setParameter(index, en.getValue());
						index++;
					}else if(en.getKey().contains("(") && en.getKey().contains(")") && en.getKey().contains(":")){
						/*
						 * // 特殊处理带有OR的模糊查询 (code LIke :searchStr AND cnName LIKE :searchStr
						 * OR shortName LIKE :searchStr) String key = en.getKey().trim(); int
						 * beginIndex = key.indexOf(":") + 1; int endIndex = key.indexOf(" ",
						 * beginIndex);
						 */
						String key = en.getKey().trim();
						int beginIndex = key.indexOf(":") + 1;
						int endIndex = key.indexOf(" ",beginIndex);
						String paramsKey = key.substring(beginIndex,endIndex);
						query1.setParameter(paramsKey, en.getValue());
						query2.setParameter(paramsKey, en.getValue());
					}
				}
			}
		}
		int page = (int) params.get("page");
		int rows = (int) params.get("rows");
		//设置分页，如果page和rows都为-1时，不分页获取所有的
		if(page != -1 && rows != -1){
			//设置第一条数据的其实索引
			query1.setFirstResult((page-1)*rows);
			//设置最多条数据的数量
			query1.setMaxResults(rows);
		}
		List<T> record = query1.list();
		int count = ((Long) query2.uniqueResult()).intValue();

		Page<T> newPage = new Page<T>(page,(count+rows-1)/rows, count, record);
		return newPage;
	}
	
	/**
	 * 模糊查询获取分页的数据，不分页page和rows设置为0就行
	 * @update XieXing
	 * @param params
	 * @param orderOrGroupBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> getPageListByMohu(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		StringBuffer sb1 = new StringBuffer("FROM " + className);
		StringBuffer sb2 = new StringBuffer("SELECT COUNT(*) FROM " + className);
		//如果限制条件不为空，就拼接参数
		if(params != null && params.size() != 0 && params.size() > 2){
			sb1.append(" WHERE");
			sb2.append(" WHERE");
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					sb1.append(" " + en.getKey() + " OR");
					sb2.append(" " + en.getKey() + " OR");
				}
			}
			//去除最后一个多余的"OR"字符串
			sb1.delete(sb1.lastIndexOf(" OR"), sb1.length());
			sb2.delete(sb2.lastIndexOf(" OR"), sb2.length());
			//日志输出打印的hql语句
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb1.toString());
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb2.toString());
		}
		if(orderOrGroupBy != null && orderOrGroupBy.size() != 0){
			for(Entry<String,String> en:orderOrGroupBy.entrySet()){
				//orderOrGroupBy中key为hql语句的group by 或者 order by （格式orderOrGroupBy.put("group by","id");）
				//orderOrGroupBy中value为hql语句的列
				//跳过page和rows的拼接，需要单独拼接
				sb1.append(" " + en.getKey() + " "+en.getValue());
				sb2.append(" " + en.getKey() + " "+en.getValue());
			}
		}
		Query query1 = getSession().createQuery(sb1.toString());
		Query query2 = getSession().createQuery(sb2.toString());
		//如果限制条件不为空，设置查询参数
		if(params != null && params.size() != 0){
			//记录用索引找参数的query.setParameter(int arg0, Object arg1);
			int index = 0;
			for(Entry<String,Object> en:params.entrySet()){
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					if(en.getKey().contains("?")){
						//带有?的hql语句（格式：params.put("id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
						query1.setParameter(index, en.getValue());
						query2.setParameter(index, en.getValue());
						index++;
					}else if((en.getKey().toUpperCase()).contains(" IN")){
						//带有in的hql语句（格式：params.put("id in (:id)",{2,3});）方法：query.setParameterList(String arg0, Object[] arg1)
						String key = en.getKey();
						query1.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
						query2.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
					}else if(en.getKey().contains("(") && en.getKey().contains(")") && en.getKey().contains(":")){
						/*
						 * // 特殊处理带有OR的模糊查询 (code LIke :searchStr AND cnName LIKE :searchStr
						 * OR shortName LIKE :searchStr) String key = en.getKey().trim(); int
						 * beginIndex = key.indexOf(":") + 1; int endIndex = key.indexOf(" ",
						 * beginIndex);
						 */
						String key = en.getKey().trim();
						int beginIndex = key.indexOf(":") + 1;
						int endIndex = key.indexOf(" ",beginIndex);
						String paramsKey = key.substring(beginIndex,endIndex);

						query1.setParameter(paramsKey, en.getValue());
						query2.setParameter(paramsKey, en.getValue());
					}
				}
			}
		}
		int page = (int) params.get("page");
		int rows = (int) params.get("rows");
		//设置分页，如果page和rows都为-1时，不分页获取所有的
		if(page != -1 && rows != -1){
			//设置第一条数据的其实索引
			query1.setFirstResult((page-1)*rows);
			//设置最多条数据的数量
			query1.setMaxResults(rows);
		}
		List<T> record = query1.list();
		int count = ((Long) query2.uniqueResult()).intValue();

		Page<T> newPage = new Page<T>(page,(count+rows-1)/rows, count, record);
		return newPage;
	}

	/**
	 * 获取分页的数据，不分页page和rows设置为0就行
	 * @param params
	 * @param orderOrGroupBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		StringBuffer sb1 = new StringBuffer("FROM " + className);
		StringBuffer sb2 = new StringBuffer("SELECT COUNT(*) FROM " + className);
		//如果限制条件不为空，就拼接参数
		if(params != null && params.size() != 0){
			sb1.append(" WHERE");
			sb2.append(" WHERE");
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					sb1.append(" " + en.getKey() + " AND");
					sb2.append(" " + en.getKey() + " AND");
				}
			}
			//去除最后一个多余的"AND"字符串
			sb1.delete(sb1.lastIndexOf(" AND"), sb1.length());
			sb2.delete(sb2.lastIndexOf(" AND"), sb2.length());
			//日志输出打印的hql语句
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb1.toString());
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb2.toString());
		}
		if(orderOrGroupBy != null && orderOrGroupBy.size() != 0){
			for(Entry<String,String> en:orderOrGroupBy.entrySet()){
				//orderOrGroupBy中key为hql语句的group by 或者 order by （格式orderOrGroupBy.put("group by","id");）
				//orderOrGroupBy中value为hql语句的列
				//跳过page和rows的拼接，需要单独拼接
				sb1.append(" " + en.getKey() + " "+en.getValue());
				sb2.append(" " + en.getKey() + " "+en.getValue());
			}
		}
		Query query1 = getSession().createQuery(sb1.toString());
		Query query2 = getSession().createQuery(sb2.toString());
		//如果限制条件不为空，设置查询参数
		if(params != null && params.size() != 0){
			//记录用索引找参数的query.setParameter(int arg0, Object arg1);
			int index = 0;
			for(Entry<String,Object> en:params.entrySet()){
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					if(en.getKey().contains("?")){
						//带有?的hql语句（格式：params.put("id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
						query1.setParameter(index, en.getValue());
						query2.setParameter(index, en.getValue());
						index++;
					}else if((en.getKey().toUpperCase()).contains(" IN")){
						//带有in的hql语句（格式：params.put("id in (:id)",{2,3});）方法：query.setParameterList(String arg0, Object[] arg1)
						String key = en.getKey();
						query1.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
						query2.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
					}else if(en.getKey().contains("(") && en.getKey().contains(")") && en.getKey().contains(":")){
						/*
						 * // 特殊处理带有OR的模糊查询 (code LIke :searchStr AND cnName LIKE :searchStr
						 * OR shortName LIKE :searchStr) String key = en.getKey().trim(); int
						 * beginIndex = key.indexOf(":") + 1; int endIndex = key.indexOf(" ",
						 * beginIndex);
						 */
						String key = en.getKey().trim();
						int beginIndex = key.indexOf(":") + 1;
						int endIndex = key.indexOf(" ",beginIndex);
						String paramsKey = key.substring(beginIndex,endIndex);

						query1.setParameter(paramsKey, en.getValue());
						query2.setParameter(paramsKey, en.getValue());
					}
				}
			}
		}
		if(params!=null && params.get("page")!=null && params.get("rows")!=null){
			int page = (int) params.get("page");
			int rows = (int) params.get("rows");
			//设置分页，如果page和rows都为-1时，不分页获取所有的
			if(page != -1 && rows != -1){
				//设置第一条数据的其实索引
				query1.setFirstResult((page-1)*rows);
				//设置最多条数据的数量
				query1.setMaxResults(rows);
			}
		}
		List<T> record = query1.list();
		return record;
	}

	/**
	 * 根据id获取实体
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getById(int id) {
		List<T> list = getSession().createQuery("FROM " + className +" WHERE id = :id").setParameter("id", id).list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 根据某个参数获取实体，该参数只能是和主键一样，是唯一的值，其参数只能一个（格式：query.setParameter(String arg0, Object arg1)）
	 * @param params
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T getByParams(Map<String, Object> params) {
		StringBuffer sb = new StringBuffer("FROM " + className);
		if(params != null && params.size() >0){
			sb.append(" WHERE");
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				sb.append(" " + en.getKey() + " AND");
			}
			sb.delete(sb.lastIndexOf(" AND"), sb.length());
		}
		Query query = getSession().createQuery(sb.toString());
		int index = 0;
		if(params != null && params.size() >0){
			for(Entry<String,Object> en:params.entrySet()){
				if(en.getKey().contains("?")){
					//带有?的hql语句（格式：params.put("id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
					query.setParameter(index, en.getValue());
					index++;
				}
			}
		}
		List<T> list = query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	/**
	 * 批量获取ids集合
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getByIds(Integer[] ids) {
		List<T> list = getSession().createQuery("FROM " + className +" WHERE id IN (:ids)").setParameterList("ids", ids).list();
		return list;
	}


	/**
	 * 获取不分页的数据，不分页page和rows设置为0就行，可以设置 是and 还是or or的在and之间用括号包住 例如: and (。。。or。。。) and
	 * @param params
	 * @param orderOrGroupBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getLikeList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		StringBuffer sb1 = new StringBuffer("FROM " + className + " WHERE 1 = 1");
		StringBuffer sb2 = new StringBuffer("SELECT COUNT(*) FROM " + className + " WHERE 1 = 1");
		//如果限制条件不为空，就拼接参数
		if(params != null && params.size() != 0){
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					sb1.append(" " + en.getKey());
					sb2.append(" " + en.getKey());
				}
			}
			//日志输出打印的hql语句
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb1.toString());
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb2.toString());
		}
		if(orderOrGroupBy != null && orderOrGroupBy.size() != 0){
			for(Entry<String,String> en:orderOrGroupBy.entrySet()){
				//orderOrGroupBy中key为hql语句的group by 或者 order by （格式orderOrGroupBy.put("group by","id");）
				//orderOrGroupBy中value为hql语句的列
				//跳过page和rows的拼接，需要单独拼接
				sb1.append(" " + en.getKey() + " "+en.getValue());
				sb2.append(" " + en.getKey() + " "+en.getValue());
			}
		}
		Query query1 = getSession().createQuery(sb1.toString());
		Query query2 = getSession().createQuery(sb2.toString());
		//如果限制条件不为空，设置查询参数
		if(params != null && params.size() != 0){
			//记录用索引找参数的query.setParameter(int arg0, Object arg1);
			int index = 0;
			for(Entry<String,Object> en:params.entrySet()){
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					if(en.getKey().contains("?")){
						//带有?的hql语句（格式：params.put("and id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
						query1.setParameter(index, en.getValue());
						query2.setParameter(index, en.getValue());
						index++;
					}else if((en.getKey().toUpperCase()).contains(" IN")){
						//带有in的hql语句（格式：params.put("and id in (:id)",{2,3});）方法：query.setParameterList(String arg0, Object[] arg1)
						String key = en.getKey();
						query1.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
						query2.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
					}
				}
			}
		}
		if(params!=null && params.get("page")!=null && params.get("rows")!=null){
			int page = (int) params.get("page");
			int rows = (int) params.get("rows");
			//设置分页，如果page和rows都为-1时，不分页获取所有的
			if(page != -1 && rows != -1){
				//设置第一条数据的其实索引
				query1.setFirstResult((page-1)*rows);
				//设置最多条数据的数量
				query1.setMaxResults(rows);
			}
		}
		List<T> record = query1.list();
		return record;
	}

	/**
	 * 获取分页的数据，不分页page和rows设置为0就行，可以设置 是and 还是or or的在and之间用括号包住 例如: and (。。。or。。。) and
	 * @param params
	 * @param orderOrGroupBy
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<T> getLikePageList(Map<String, Object> params,
			Map<String, String> orderOrGroupBy) {
		StringBuffer sb1 = new StringBuffer("FROM " + className + " WHERE 1 = 1");
		StringBuffer sb2 = new StringBuffer("SELECT COUNT(*) FROM " + className + " WHERE 1 = 1");
		//如果限制条件不为空，就拼接参数
		if(params != null && params.size() != 0){
			for(Entry<String,Object> en:params.entrySet()){
				//params中key为hql语句的where条件,value为条件注入的参数（格式：params.put("id = ?","12");）
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					sb1.append(" " + en.getKey());
					sb2.append(" " + en.getKey());
				}
			}
			//日志输出打印的hql语句
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb1.toString());
			log.debug("Class: "+this.getClass().getName()+" method: "+
					Thread.currentThread().getStackTrace()[1].getMethodName() +" line:"+
					Thread.currentThread().getStackTrace()[1].getLineNumber()+" out:"+sb2.toString());
		}
		if(orderOrGroupBy != null && orderOrGroupBy.size() != 0){
			for(Entry<String,String> en:orderOrGroupBy.entrySet()){
				//orderOrGroupBy中key为hql语句的group by 或者 order by （格式orderOrGroupBy.put("group by","id");）
				//orderOrGroupBy中value为hql语句的列
				//跳过page和rows的拼接，需要单独拼接
				sb1.append(" " + en.getKey() + " "+en.getValue());
				sb2.append(" " + en.getKey() + " "+en.getValue());
			}
		}
		Query query1 = getSession().createQuery(sb1.toString());
		Query query2 = getSession().createQuery(sb2.toString());
		//如果限制条件不为空，设置查询参数
		if(params != null && params.size() != 0){
			//记录用索引找参数的query.setParameter(int arg0, Object arg1);
			int index = 0;
			for(Entry<String,Object> en:params.entrySet()){
				//跳过page和rows的拼接，需要单独拼接
				if(!"page".equals(en.getKey()) && !"rows".equals(en.getKey())){
					if(en.getKey().contains("?")){
						//带有?的hql语句（格式：params.put(" and id = ?","12");）方法：query.setParameter(int arg0, Object arg1)
						query1.setParameter(index, en.getValue());
						query2.setParameter(index, en.getValue());
						index++;
					}else if((en.getKey().toUpperCase()).contains(" IN")){
						//带有in的hql语句（格式：params.put(" and id in (:id)",{2,3});）方法：query.setParameterList(String arg0, Object[] arg1)
						String key = en.getKey();
						query1.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
						query2.setParameterList(key.substring(key.indexOf("(")+2,key.indexOf(")")).trim(), (Object[])en.getValue());
					}
				}
			}
		}
		int page = 0;
		int rows = 0;
		if(params!=null && params.get("page")!=null && params.get("rows")!=null){
			page = (int) params.get("page");
			rows = (int) params.get("rows");
			//设置分页，如果page和rows都为-1时，不分页获取所有的
			if(page != -1 && rows != -1){
				//设置第一条数据的其实索引
				query1.setFirstResult((page-1)*rows);
				//设置最多条数据的数量
				query1.setMaxResults(rows);
			}
		}
		List<T> record = query1.list();
		int count = ((Long) query2.uniqueResult()).intValue();

		Page<T> newPage = new Page<T>(page,(count+rows-1)/rows, count, record);
		return newPage;
	}






}
