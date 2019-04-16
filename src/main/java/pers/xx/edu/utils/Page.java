package pers.xx.edu.utils;

import java.util.List;

/**
 * 
 * @description 对JqGrid的数据进行分页格式化 
 * @author XieXing
 * @create 2018-4-10上午11:45:55
 * @param <T>
 *
 */
public class Page<T> {
	
	private int page;//当前页
	
	private int total;//总页数
	
	private int records;//总记录数
		
	private List<T> rows;//记录列表

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRecords() {
		return records;
	}

	public void setRecords(int records) {
		this.records = records;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "JqGridPage [page=" + page + ", total=" + total + ", records="
				+ records + ", rows=" + rows + ", getPage()=" + getPage()
				+ ", getTotal()=" + getTotal() + ", getRecords()="
				+ getRecords() + ", getRows()=" + getRows() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public Page(int page, int total, int rows, List<T> recordsList) {
		this.page = page;
		this.total = total;
		this.records = rows;
		this.rows = recordsList;
	}
	
	public Page() {
		
	}

	/**
	 * @description 把数据格式化为JqGrid所需要的解析格式
	 * @return
	 */
	public String toJqGridString() {
		return "{"
				+"\"page\":"+"\""+getPage()+"\","
				+"\"total\":"+"\""+getTotal()+"\","
				+"\"records\":"+"\""+getRecords()+"\","
				+"\"rows\":"+JsonUtils.toJson(getRows())
				+"}";
	}
	
	/**
	 * @description 把数据格式化为JqGrid所需要的解析格式
	 * @return
	 */
	public String toJqGridString(String type) {
		return "{"
				+"\"page\":"+"\""+getPage()+"\","
				+"\"total\":"+"\""+getTotal()+"\","
				+"\"records\":"+"\""+getRecords()+"\"," 
				+"\"type\":"+"\""+type+"\","
				+"\"rows\":"+JsonUtils.toJson(getRows())
				+"}";
	}
}
