package udpChat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Scanner;

public class UDPChatClient {
	
	private DatagramSocket clientSocket = null;
	byte[] receiveData = new byte[1024];
	byte[] sendData = new byte[1024];
	InetAddress IPAddress = null;
	int port = 9876;
	private String inputStr;
	private String outputStr;
	
	public UDPChatClient(int port) {
		try {
			clientSocket = new DatagramSocket();
			IPAddress = InetAddress.getByName("localhost");
			System.out.println("Client stated");
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
				// toDo ham nhan packet
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				//nhan du lieu tu server
				clientSocket.receive(receivePacket);
				//lay dia chi ip cua may cl
				inputStr = new String(receivePacket.getData());
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
				outputStr = keyboard.nextLine();
				sendData = ("Client: "+ outputStr).getBytes();
				if(IPAddress != null) {
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress, port);
					//gui dl lai cho client
					clientSocket.send(sendPacket);
					keyboard = keyboard.reset();
				}
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
//		DatagramSocket clientSocket = new DatagramSocket(); //gan cong
//		InetAddress IPAddress = InetAddress.getByName("localhost");
//		byte[] sendData = new byte[1024];
//		byte[] receiveData = new byte[1024];
//		
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			System.out.print("Client: ");
//			String msg = sc.nextLine();
//			
//			sendData = ("Client: "+msg).getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,9876);
//			clientSocket.send(sendPacket); // gui dl cho server
//			
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			//nhan dl tu server
//			clientSocket.receive(receivePacket);
//			//lay du lieu tu packet nhan dc
//			String str = new String(receivePacket.getData());
//			System.out.println(str);
//			sc = sc.reset();
//		}
		new UDPChatClient(9876);
	}

}
