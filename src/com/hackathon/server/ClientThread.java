package com.hackathon.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Collection;

public class ClientThread implements Runnable {

	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String nick;
	
	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	@Override
	public void run() {
		while (true) {
			String rawMsg = null;
			try {
				rawMsg = in.readUTF();
				process(rawMsg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void process(String rawMsg) {
		String[] packet = rawMsg.split(":");
		String code = packet[0];
		
		if (code.equals("1")) {
			nick = packet[1];
			Server.map.put(nick, this);
		} else if (code.equals("2")) {
			String dest = packet[1];
			String msg = packet[2];
			Server.map.get(dest).send(msg);			
		} else {
			send("protocol error");
		}
	}

	private void send(String s) {
		// TODO Auto-generated method stub
		try {
			out.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
