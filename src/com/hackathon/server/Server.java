package com.hackathon.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {

	private static int userId = 1;
	public static Map<String,ClientThread> clients = new HashMap();
	
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(5000);

		while(true){
			Socket socket = serverSocket.accept();
			ClientThread client = new ClientThread(socket);
			new Thread(client).start();
		}
	}
}
