package com.hackathon.server;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {



	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(5000);

		 
		while(true){
			Socket  socket = serverSocket.accept();
			new Thread(new ClientThread(socket)).start();;
			
		}
	}
	
	
}
