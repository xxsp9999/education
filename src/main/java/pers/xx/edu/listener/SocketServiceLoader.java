package pers.xx.edu.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import pers.xx.edu.socket.SocketThread;

/**
 *
 * @description socket随着tomcat启动而启动
 * @author 白贵才
 * @create 2017-11-8下午5:02:10
 *
 */
public class SocketServiceLoader implements ServletContextListener {

	//socket server 线程
	private SocketThread socketThread;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		if(socketThread != null && !socketThread.isInterrupted()){
			socketThread.closeSocketServer();
			socketThread.isInterrupted();
		}
		System.out.println("关闭tomcat");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("启动tomcat");
		if(socketThread == null){
			//新建个socket的线程
			socketThread = new SocketThread(null);

			//启动线程
			socketThread.start();
		}
		//new SocketLoopThread().run();

	}

}
