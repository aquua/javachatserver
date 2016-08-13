package com.hackathon.client;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JFrame;

// ref
// https://www.youtube.com/watch?v=fSQOhFYgE3s

public class ChatClientGUI extends JFrame
implements WindowListener, MouseListener, KeyListener{
	
	private TextArea msgArea = null;
	private TextField sendArea = null;
	private DataOutputStream out = null;
	
	public ChatClientGUI(String title, DataOutputStream out) {
		super(title);
		
		this.addWindowListener(this);
		this.setSize(800, 600);
		this.setResizable(true);
		this.setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		msgArea = new TextArea();
		msgArea.setEditable(false);
		this.add(msgArea, "Center");
		msgArea.setFont(new Font("Arial", Font.PLAIN, 16));
		
		Panel p = new Panel();
		p.setLayout(new FlowLayout());
		
		sendArea = new TextField(30);
		sendArea.addKeyListener(this);
		sendArea.setFont(new Font("Arial", Font.PLAIN, 16));
		
		p.add(sendArea);
		p.setBackground(new Color(211, 211, 211));
		
		Button send = new Button("Send");
		send.addMouseListener(this);
		p.add(send);
		
		this.add(p, "South");
		this.setVisible(true);
		
		this.out = out;
		
	}
	
	public void pipeToMsgArea(DataInputStream in) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while (true) {
					try {
						String rawMsg = in.readUTF();
						System.out.println("received from server : " + rawMsg);
						msgArea.append(rawMsg + "\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		String msg = sendArea.getText();
		sendArea.setText("");
		if (msg != "") {
			try {
				out.writeUTF(msg);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
