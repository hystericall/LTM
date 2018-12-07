package tcpChat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	
	private ServerSocket server = null;
	private Socket socket = null;
	private DataOutputStream dos = null;
	private DataInputStream din = null;
	private String inputStr;
	private String outputStr;
	
	public ChatServer(int port) {
		try {
			ServerSocket server = new ServerSocket(port);
			System.out.println("Server stated");
			socket = server.accept();
			System.out.println("Client connected");
			dos = new DataOutputStream(socket.getOutputStream());
			din = new DataInputStream(socket.getInputStream());
			Thread t1 = new Thread() {
				
				@Override
				public void run() {
					receiveMsg();
					
				}
			};
			Thread t2 = new Thread() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					sendMsg();
				}
			};
			t1.start();
			t2.start();
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	public void receiveMsg() {
		try {
			while(true) {
				inputStr = din.readUTF();
				System.out.println(inputStr);
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	
	public void sendMsg() {
		try {
			Scanner keyboard = new Scanner(System.in);
			while(true) {
				System.out.print("Server: ");
				outputStr = keyboard.nextLine();
				dos.writeUTF("Server: " + outputStr);
				dos.flush();
				keyboard = keyboard.reset();
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	public static void main(String[] args) throws Exception {
//		ServerSocket server = new ServerSocket(7000);
//		System.out.println("Server stated");
//		Socket sk = server.accept();
//		DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
//		DataInputStream din = new DataInputStream(sk.getInputStream());
//		Scanner kb = new Scanner(System.in);
//		while(true) {
//			String st = din.readUTF();
//			System.out.println(st);
//			System.out.println("Server: ");
//			String msg = kb.nextLine();
//			dos.writeUTF("Server: " + msg);
//			dos.flush();
//			kb = kb.reset();
//		}
		new ChatServer(5000);
	}
}
