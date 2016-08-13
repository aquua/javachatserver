package com.hackathon.client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientOutputThread implements Runnable {

	private Socket sock;
	private DataOutputStream out;
	
	public ClientOutputThread(Socket sock) throws IOException {
		this.sock = sock;
		out = new DataOutputStream(sock.getOutputStream());
	}
	@Override
	public void run() {
		while (true) {
			Scanner scan = new Scanner(System.in);
			try {
				out.writeUTF(scan.nextLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
