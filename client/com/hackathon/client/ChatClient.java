package com.hackathon.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String serverName = "localhost";
		int port = 5000;
		Socket client = null;
				
		try {	
			client = new Socket(serverName, port);
			new Thread(new ClientOutputThread(client)).start();			
			DataInputStream in = new DataInputStream(client.getInputStream());
			
			while (true) {
				System.out.println(in.readUTF());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
