package pers.xx.edu.listener;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import pers.xx.edu.entity.MessageTran;
import pers.xx.edu.service.MessageService;
import pers.xx.edu.utils.HexStringUtils;
import pers.xx.edu.utils.SpringContextUtil;

public class SocketLoopThread extends Thread{
	
	private MessageService messageService;
	
	
	public SocketLoopThread() {
		
		// 在线程中是不能直接从容器中获取bean的，需要另写一个工具类来获取
          
         
        this.messageService = SpringContextUtil.getBean("messageService");
	}


	@Override
	public void run() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				
				
				Socket socket = null;
		        try {
		        		socket  = new Socket("192.168.1.178",4001);
		        		//获取输入流，用于读取来自服务端的消息
//		        		InputStream in = socket.getInputStream();
//		        		BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
//		        		
//		        		//获取输出流，用于向服务端发送消息
//		        		OutputStream out = socket.getOutputStream();
//		        		OutputStreamWriter osw = new OutputStreamWriter(out,"UTF-8");
//		        		PrintWriter writer= new PrintWriter(osw,true);
//		        		
//		        		String str = "A0 04 01 89 01 D1";
		        		byte[] bytes = {(byte) Integer.parseInt("A0", 16),
		        				(byte) Integer.parseInt("04", 16),
		        				(byte) Integer.parseInt("01", 16),
		        				(byte) Integer.parseInt("89", 16),
		        				(byte) Integer.parseInt("01", 16),
		        				(byte) Integer.parseInt("D1", 16)}; // 对应的十六进制是 0F 10 11 78
		        		System.out.println("发送十六进制："+HexStringUtils.bytesToHex(bytes, 0, bytes.length));
		        		//向服务端发送一个字符串
		        		//writer.println(bytes);
		        		DataOutputStream dos // 建立输出流
				                = new DataOutputStream(socket.getOutputStream());
		        			dos.write(bytes, 0, bytes.length);
		        		
		        		
		        		
						DataInputStream dis = new DataInputStream(socket.getInputStream());
				            byte[] bytes2 = new byte[4096]; // 假设发送的字节数不超过 1024 个
				            int size = dis.read(bytes2); // size 是读取到的字节数
				            
				            String hex = HexStringUtils.bytesToHex(bytes2, 0, size);
//				            List<MessageTran> list = new ArrayList<MessageTran>();
				            
				            if(size%21 == 0){
				            	int i = size / 21;
				            	int k = 0;
				            	for(k = 0;k<i;k++){
				            		MessageTran tran = new MessageTran();
				            		tran.setBtPacketType(HexStringUtils.byteToHex(bytes2[0+21*k]));
				            		tran.setBtDataLen(HexStringUtils.byteToHex(bytes2[1+21*k]));
				            		tran.setBtReadId(HexStringUtils.byteToHex(bytes2[2+21*k]));
				            		tran.setBtCmd(HexStringUtils.byteToHex(bytes2[3+21*k]));
				            		
				            		tran.setBtCheck(HexStringUtils.byteToHex(bytes2[20+21*k]));
				            		
				            		String btAryTranData = "";
				            		for(int p = 0;p < 12;p++){
				            			btAryTranData += HexStringUtils.byteToHex(bytes2[p+7+21*k])+" ";
			            			}
				            		tran.setBtAryTranData(btAryTranData.substring(0, btAryTranData.length()-1));
				            		
				            		Map<String,Object> params = new HashMap<String,Object>();
				            		params.put("btAryTranData = ? ", btAryTranData);
				            		List<MessageTran> list2 = messageService.getList(params, null);
				            		if(list2 == null || list2.size() == 0){
				            			messageService.save(tran);
				            			System.out.println("保存成功");
				            		}
				            		
				            		//tran.setBtAryData(btAryData);
				            		
			            			//System.out.println("Head:"+byteToHex(bytes2[0+21*k]));
			            			//System.out.println("Len:"+byteToHex(bytes2[1+21*k]));
			            			//System.out.println("Address:"+byteToHex(bytes2[2+21*k]));
			            			//System.out.println("Cmd:0x"+byteToHex(bytes2[3+21*k]));
			            		//	System.out.println("FreqAnt:"+byteToHex(bytes2[4+21*k]));
			            		//	System.out.println("PC:"+byteToHex(bytes2[5+21*k])+" "+byteToHex(bytes2[6+21*k]));
			            		//	System.out.println("EPC:");
			            			for(int p = 7;p < 19;p++){
			            				System.out.print(HexStringUtils.byteToHex(bytes2[p+21*k])+" ");
			            			}
			            			System.out.println("");
			            		//	System.out.println("RSSI:"+byteToHex(bytes2[19+21*k]));
			            		//	System.out.println("Check:"+byteToHex(bytes2[20+21*k]));
			            			
			            			
				            	}
				            }
				            //System.out.println(tran);
				            
				            System.out.println("接收到的byte数组的十六进制：" + size +"--" +hex);
				        
		        } catch (Exception e) {
		            e.printStackTrace();
		        } finally{
		            try {
		                if(socket != null){
		                    //关闭Socket
		                    socket.close();
		                }
		            } catch (IOException e) {
		                e.printStackTrace();
		            }
		        }
			}
		}, 1000, 500);
	}

	
}
