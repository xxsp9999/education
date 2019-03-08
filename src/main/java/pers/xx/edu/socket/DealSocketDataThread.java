package pers.xx.edu.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 
 * @description socket数据处理
 * @author 白贵才
 * @create 2017-11-8下午5:22:33
 *
 */
public class DealSocketDataThread extends Thread{

	private Socket socket;
	
	public DealSocketDataThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try{
			InputStream in = socket.getInputStream();
			 BufferedReader reader
             = new BufferedReader(
                 new InputStreamReader(
                     in,"UTF-8"
                 )
             );
			 System.out.println("socket:"+reader.readLine());
			 
			 socket.close(); 
			 System.out.println("socket stop....."); 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
	}

}
