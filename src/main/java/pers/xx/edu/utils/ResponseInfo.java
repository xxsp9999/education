package pers.xx.edu.utils;
/**
 * @author xiexing
 * @description 响应信息工具类
 * @create 2018年11月17日 下午1:53:25
 */
public class ResponseInfo {

	private boolean status;// 请求状态

	private String messgae;// 返回信息

	public ResponseInfo() {
		super();
	}

	/**
	 * @param status
	 * @param messgae
	 */
	public ResponseInfo(boolean status, String messgae) {
		super();
		this.status = status;
		this.messgae = messgae;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMessgae() {
		return messgae;
	}

	public void setMessgae(String messgae) {
		this.messgae = messgae;
	}

	@Override
	public String toString() {
		return "ResponseInfo [status=" + status + ", messgae=" + messgae + "]";
	}
}
