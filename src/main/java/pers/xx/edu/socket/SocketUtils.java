package pers.xx.edu.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import pers.xx.edu.utils.HexStringUtils;

/**
 * 
 * @description socket 工具类 
 * @author XieXing
 * @create 2018-11-10下午2:03:36
 *
 */
public class SocketUtils {
	
	
	/**
	 * @description 写入RFID标签
	 * @param rfid 12个两位的 十六进制
	 * @param newRfid  12个两位的 十六进制
	 */
	public static boolean writeRFID(String rfid,String newRfid,String ip){
		if(checkLockResult1(getLock1(ip))){
			System.out.println("申请加锁1！");
			if(checkLockResult2(getLock2(rfid,ip))){
				System.out.println("申请加锁2！");
				if(checkWriteResult(write(newRfid,ip), rfid)){
					System.out.println("写入");
					if(checkUnLockResult(getUnLock(ip))){
						System.out.println("OKOK");
						return true;
					}else{
						if(checkUnLockResult(getUnLock(ip))){
							System.out.println("解锁OKOK");
							return false;
						}
						return false;
					}
				}else{
					if(checkUnLockResult(getUnLock(ip))){
						System.out.println("解锁OKOK");
						return false;
					}
					return false;
				}
			}else{
				if(checkUnLockResult(getUnLock(ip))){
					System.out.println("解锁OKOK");
					return false;
				}
				return false;
			}
			
		}else{
			//解锁
			if(checkUnLockResult(getUnLock(ip))){
				System.out.println("解锁OKOK");
				return false;
			}
			return false;
		}
	}
	
	public static void main(String[] args) {
		String ip = "192.168.1.178";
		String rfid = "00 00 00 00 00 00 00 00 00 00 00 04";
		String newRfid = "01 01 01 01 01 01 01 01 01 01 01 01";
		if(checkLockResult1(getLock1(ip))){
			System.out.println("申请加锁1！");
			if(checkLockResult2(getLock2(rfid,ip))){
				System.out.println("申请加锁2！");
				if(checkWriteResult(write(newRfid,ip), rfid)){
					System.out.println("写入");
					if(checkUnLockResult(getUnLock(ip))){
						System.out.println("OKOK");
					}
				}
			}
			
		}else{
			//解锁
			if(checkUnLockResult(getUnLock(ip))){
				System.out.println("解锁OKOK");
			}
		}
		
	}

	
	/**
	 * @description socket 发送命令和接收回复
	 * @param cmdHex
	 * @return
	 */
	public static String getSocket(String cmdHex,String ip){
		Socket socket = null;
		try {
			socket = new Socket(ip,4001);
    		
    		cmdHex = HexStringUtils.formatStr(cmdHex);
    		
    		if (cmdHex == null || cmdHex.length() == 0) {
    			socket.close();
				return null;
			} 
    		String[] hexs = cmdHex.split(" ");
    		
    		byte[] bytes = new byte[(cmdHex.length()+1)/3];
    		for (int i = 0;i < hexs.length;i++) {
				bytes[i] = (byte) Integer.parseInt(hexs[i],16);
			}
    		
    		//获取输出流，用于向服务端发送消息
    		//向服务端发送一个字符串
    		DataOutputStream dos // 建立输出流
	                = new DataOutputStream(socket.getOutputStream());
			dos.write(bytes, 0, bytes.length);
    		
			DataInputStream dis = new DataInputStream(socket.getInputStream());
            byte[] bytes2 = new byte[4096]; // 假设发送的字节数不超过 1024 个
            int size = dis.read(bytes2); // size 是读取到的字节数
            String hex = HexStringUtils.bytesToHex(bytes2, 0, size);
            if((hex.substring(hex.length()-1, hex.length())).equals(" ")){
            	return hex.substring(0, hex.length()-1);
            }
            return hex;
            
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			
		}finally{
			 try {
                if(socket != null){
                    //关闭Socket
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
		}
		
		return null;
	}
	
	
    /**
     * @description 锁定声明;
     * @param rfid
     * @return
     */
    private static String getLock1(String ip){
    	String cmdHex = "A0 03 01 86 D6";
    	String result = getSocket(cmdHex,ip);
    	return result;
    }
    
    /**
     * @description 验证锁定声明的结果
     * @param rfid
     * @return
     */
    @SuppressWarnings("unused")
	private static boolean checkLockResult1(String result){
    	//失败 A0 11 01 86 00 0C 00 00 00 00 00 00 00 00 00 00 00 02 BA
    	String resultHex = "A0 04 01 86 01 D4";
    	return resultHex.equals(result);
    }
    
    
    /**
     * @description 锁定
     * @param rfid
     * @return
     */
    private static String getLock2(String rfid,String ip){
    	//A0 11 01 85 00 0C 00 00 00 00 00 00 00 00 00 00 00 02 BB
    	String head = "A0 11 01 85 00 0C";
    	String total = head + rfid;
    	String formatStr = HexStringUtils.formatStr(total);
    	String checkResult = HexStringUtils.checkStr(formatStr);
    	
    	String cmdHex = formatStr + " " + checkResult;
    	System.out.println(cmdHex);
    	
    	String result = getSocket(cmdHex,ip);
    	return result;
    }
	
    /**
     * @description 验证锁定的结果
     * @param rfid
     * @return
     */
    @SuppressWarnings("unused")
	private static boolean checkLockResult2(String result){
    	String resultHex = "A0 04 01 85 10 C6";
    	return resultHex.equals(result);
    }
    
    
    /**
     * @description 写入
     * @param newRfid
     * @return
     */
    private static String write(String newRfid,String ip){
    	
    	//A0 16 01 82 00 00 00 00 01 02 06 01 01 01 01 01 01 01 01 01 01 01 01 B2
    	String head = "A0 16 01 82 00 00 00 00 01 02 06";
    	String total = head + newRfid;
    	String formatStr = HexStringUtils.formatStr(total);
    	String checkResult = HexStringUtils.checkStr(formatStr);
    	
    	String cmdHex = formatStr + " " + checkResult;
    	System.out.println(cmdHex);
    	String result = getSocket(cmdHex,ip);
    	return result;
    }
    
    /**
     * @description 写入结果验证
     * @param newRfid
     * @return
     */
    @SuppressWarnings({ "unused", "unused" })
	private static boolean checkWriteResult(String result,String rfid){
    	//result:    A0 19 01 82 00 01 10 30 00 00 00 00 00 00 00 00 00 00 00 00 02 2D EF 10 8C 01 C8
    	// resultHex:A0 19 01 82 00 01 10 30 00 00 00 00 00 00 00 00 00 00 00 00 02 09 D8 10 50 01 3F
    	//失败 A0 04 01 82 36 A3
    	String head = "A0 19 01 82 00 01 10 30 00";
    	String end = "09 D8 10 50 01";
    	String total = head + rfid + end;
    	String formatStr = HexStringUtils.formatStr(total);
    	String checkResult = HexStringUtils.checkStr(formatStr);
    	
    	//String resultHex = formatStr + " " + checkResult;
    	if("A0 04 01 82 36 A3".equals(result)){
    		return false;
    	}else{
    		return true;
    	}
    /*	String resultHex = HexStringUtils.formatStr(head+rfid);
    	return result.contains(resultHex);*/
    }
    
    
    /**
     * @description 解锁
     * @return
     */
    private static String getUnLock(String ip){
    	String cmdHex = "A0 04 01 85 01 D5";
    	String result = getSocket(cmdHex,ip);
    	return result;
    }
	
    /**
     * @description 验证解锁的结果
     * @param rfid
     * @return
     */
    @SuppressWarnings("unused")
	private static boolean checkUnLockResult(String result){
    	String resultHex = "A0 04 01 85 10 C6";
    	return resultHex.equals(result);
    }
}
