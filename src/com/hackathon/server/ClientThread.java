package com.hackathon.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class ClientThread implements Runnable {

	
	
	private Socket socket;
	public ClientThread(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		OutputStream out = null;
		DataOutputStream dos = null;
		
		try {
			out = socket.getOutputStream();
			dos = new DataOutputStream(out); 
			dos.writeUTF("서버로부터의 메세지입니다.");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} finally{
			if ( dos != null )
				try {
					dos.close();
				} catch (IOException e) {}
			if ( out != null ) {
				try {
					out.close();
				} catch (IOException e) {}
			}
		}
	}

}
