package pers.xx.edu.socket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @description socket 的线程类 
 * @author 白贵才
 * @create 2017-11-8下午5:13:11
 *
 */
public class SocketThread extends Thread{

	private ServerSocket serverSocket = null; 
	
	public SocketThread(ServerSocket serverSocket) {
		try{
			if(serverSocket == null){
				this.serverSocket = new ServerSocket(9527);
				System.out.println("ServerSocket start port 9527");
			}
		}catch(Exception e){
			System.out.println("SocketThread创建socket服务出错"); 
			e.printStackTrace(); 
		}
	}



	@Override
	public void run() {
		while(true){
			try{
				System.out.println("监听。。。。");
				Socket socket = serverSocket.accept();
				
				if(socket != null){
					//处理接收的数据
					//new DealSocketDataThread(socket).start();
					InputStream in = socket.getInputStream();
		            BufferedReader reader
		                = new BufferedReader(
		                    new InputStreamReader(
		                        in,"UTF-8"
		                    )
		                );
		            System.out.println(reader.readLine());
		            socket.close(); 
				}
				//设置超时时间
				//socket.setSoTimeout(30000);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
			}
		}
	}

	
	public void closeSocketServer(){
		try{
			if(serverSocket != null && !serverSocket.isClosed()){
				serverSocket.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
