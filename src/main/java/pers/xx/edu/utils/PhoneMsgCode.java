package pers.xx.edu.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * @author XieXing
 * @description 短信验证码工具类(成都凌凯)
 * @create 2018年11月18日 下午7:53:44
 */
public class PhoneMsgCode {
	private static final String CorpID="CDCJ007854";// 接口账号(达州直工网)

	private static final String Pwd="dzzgw001";	// 接口密码
	
	/**
	 * Hppt POST请求发送方法 返回值>0 为 提交成功
	 * 
	 * @param Mobile
	 *            电话号码
	 * @param Content
	 *            发送内容
	 * @param send_time		参数格式例：20171222162230   ---代表2017年12月22日16点22分30秒
	 *            定时发送时间，为空时，为及时发送
	 * @return
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static int sendSMSPost(String Mobile, String Content,
			String send_time) throws MalformedURLException,
			UnsupportedEncodingException {

		String inputLine = "";
		int value = -2;

		String send_content = URLEncoder.encode(
				Content.replaceAll("<br/>", " "), "gbk");// 发送内容,这儿我修改了编码

		String strUrl = "https://sdk2.028lk.com/sdk2/BatchSend2.aspx";
		String param = "CorpID=" + CorpID + "&Pwd=" + Pwd + "&Mobile=" + Mobile
				+ "&Content=" + send_content + "&Cell=&SendTime=" + send_time;

		try {

			inputLine = sendPost(strUrl, param);

			System.out.println("开始发送短信手机号码为 ：" + Mobile);

			value = new Integer(inputLine).intValue();

		} catch (Exception e) {

			System.out.println("网络异常,发送短信失败！");
			value = -2;

		}

		System.out.println(String.format("返回值：%d", value));

		return value;
	}
	
	/**
	 * 向指定 URL 发送POST方法的请求
	 * 
	 * @param url
	 *            发送请求的 URL
	 * @param param
	 *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
	 * @return 所代表远程资源的响应结果
	 */
	public static String sendPost(String url, String param) {

		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";

		try {
			URL realUrl = new URL(url);
			// 打开和URL之间的连接
			URLConnection conn = realUrl.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// 获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			// 发送请求参数
			out.print(param);
			// flush输出流的缓冲
			out.flush();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("发送 POST 请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输出流、输入流
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 获取回复消息
	 * @param corpId 账号
	 * @param pwd 密码
	 * @return 回复消息结果
	 */
	public static String getReplyMsg(String corpId,String pwd){
		
		String result="";
		String msg = "";
		String strUrl = "https://sdk2.028lk.com/sdk2/Get.aspx";
		String param = "CorpID=" + corpId + "&Pwd=" + pwd;

		try {

			result = sendPost(strUrl,param);
//			result ="||15281008413#123#2017-01-04||15281008412#123#2017-01-04||15281008413#121#2017-01-04||15281008410#123#2017-01-04||";
			
			if(result==null||result.equals("")||result.length()==0){
				msg ="没有上行信息";
			}
			
			if(result.equals("-1")){
				msg = "账号未注册";
			}else if(result.equals("-2")){
				msg = "其他错误";
			}else if(result.equals("-3")){
				msg = "密码错误";
			}else if(result.equals("-101")){
				msg = "调用接口频率过快(大于30s调用一次)";
			}else if(result.equals("-100")){
				msg = "IP黑名单";
			}else if(result.equals("-102")){
				msg = "账号黑名单";
			}else if(result.equals("-103")){
				msg = "IP未导白";
				
			}else {
				
				msg = String.format("获取成功！%s",result);
				
				result = result.replace("||", "|");
				
				String [] strArray = result.split("\\|");
				String outContent = "";
				
				int i = 0;
				
				for(String str : strArray){
					
					if(str.equals(""))
						continue;
					
					String [] strConArr = str.split("#");
					
					if(strConArr ==null||strConArr.length<=0)
						continue;
					
					i = i+1;
					
					outContent = String.format("第%d条回复,手机号码：%s,回复内容：%s,回复时间：%s",
							i,
							strConArr[0],
							strConArr[1],
							strConArr[2]);
					
					System.out.println(outContent);
					
				}
			}
			
			System.out.println(msg);
			
		} catch (Exception e) {
			
			System.out.println("网络异常,回复消息获取失败！");

		}
		
		return result;
	}
}
