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
	private String nick = null;
	
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
				break;
			}
			
		}
	}
	
	private void process(String rawMsg) {
		String[] packet = rawMsg.split(":");
		String code = packet[0];
		
		// TODO protocol refac!!!!
		
		if (code.equals("1")) {
			// login with nickname;
			if (this.nick != null) {
				send("error:already have nick =" + this.nick);
			} else {
				String nick = packet[1];
				changeNick(nick);
			}
		} else if (code.equals("2")) {
			if (this.nick == null){
				send("error:please login first!");
			} else {
				// broadcast to all
				for (ClientThread client: Server.clients.values()) {
					client.send("msg:" + this.nick + ":" + packet[1]);
				}
			}
		} else {
			send("error:packet protocol error! = " + rawMsg);
		}
	}
	
	private void changeNick(String nick) {
		ClientThread client = Server.clients.get(this.nick);
		if (client != null) {
			send("error:another name please!");
		} else {
			Server.clients.put(nick, this);
			this.nick = nick;
			send("nick:" + nick);
		}
		
	}

	private void send(String s) {
		try {
			out.writeUTF(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
