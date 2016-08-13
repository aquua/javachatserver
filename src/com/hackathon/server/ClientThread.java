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
	
	public ClientThread(Socket socket) throws IOException {
		this.socket = socket;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}
	
	@Override
	public void run() {
		while (true) {
			String s = null;
			try {
				s = in.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Collection<ClientThread> clients = Server.map.values();
			for (ClientThread client : clients) {
				client.send(s);
			}
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
