package tcpChat;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClient {

	private Socket socket = null;
	private DataOutputStream dos = null;
	private DataInputStream din = null;
	private String inputStr;
	private String outputStr;
	
	public ChatClient(String host, int port) {
		try {
			socket = new Socket(host, port);
			System.out.println("Server connected");
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
		catch(NullPointerException a) {
			a.fillInStackTrace();
		}
	}
	
	public void sendMsg() {
		try {
			Scanner keyboard = new Scanner(System.in);
			while(true) {
				System.out.println("Client: ");
				outputStr = keyboard.nextLine();
				dos.writeUTF("Client: " + outputStr);
				dos.flush();
				keyboard = keyboard.reset();
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	
	public static void main(String[] args) throws Exception {
//		Socket socket = new Socket("localhost", 7000);
//		System.out.println("Connected");
//		DataInputStream din = new DataInputStream(socket.getInputStream());
//		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
//		while(true) {
//			Scanner kb = new Scanner(System.in);
//			String st = din.readUTF();
//			System.out.println(st);
//			System.out.println("Client: ");
//			String msg = kb.nextLine();
//			dos.writeUTF("Client: " + msg);
//			dos.flush();
//			kb = kb.reset();
//		}
		new ChatClient("localhost", 5000);
	}
}
