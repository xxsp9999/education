package pers.xx.edu.vo;

/**
 * @author xiexing
 * @description 通用状态信息类
 * @create 2018年10月5日 上午11:30:30
 */

import java.util.HashMap;
import java.util.Map;

public class Message {
	// 状态码 100-成功 200-失败
	private int code;

	// 提示信息信息
	private String msg;

	// 要返回给浏览器的数据
	private Map<String, Object> extend = new HashMap<String, Object>();

	// 处理成功状态信息
	public static Message success() {
		Message message = new Message();
		message.setCode(100);
		message.setMsg("处理成功！");
		return message;
	}
	
	//添加返回信息
	public Message add(String key,Object value) {
		this.extend.put(key, value);
		return this;
	}
	// 处理失败状态信息
	public static Message fail() {
		Message message = new Message();
		message.setCode(200);
		message.setMsg("处理失败！");
		return message;
	}
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Map<String, Object> getExtend() {
		return extend;
	}

	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	
	@Override
	public String toString() {
		return "Message [code=" + code + ", msg=" + msg + ", extend=" + extend
				+ "]";
	}

	public static void main(String[] args) {
		System.out.println(Message.success().add("key", "value").add("key2", "value2"));
	}
}
