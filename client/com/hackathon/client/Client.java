package com.hackathon.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {

		// network
		String host = "localhost";
		int port = 5000;

		Socket sock;
		DataInputStream in = null;
		DataOutputStream out = null;
		
		try {
			sock = new Socket(host, port);
			in = new DataInputStream(sock.getInputStream());
			out = new DataOutputStream(sock.getOutputStream());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//System.exit(0);
		}

		// gui
		ChatClientGUI gui = new ChatClientGUI("Chat Client v1.0", out);		
		
		// display msg pipe from server
		gui.pipeToMsgArea(in);
		gui.printText("server connected.");
		gui.printText("please type your name first!");
	}
}
