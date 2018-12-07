package udpChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class UDPChatServer {
	private DatagramSocket serverSocket = null;
	byte[] receiveData = new byte[1024];
	byte[] sendData = new byte[1024];
	InetAddress IPAddress = null;
	int port = -1;
	private String inputStr;
	private String outputStr;
	
	public UDPChatServer(int port) {
		try {
			serverSocket = new DatagramSocket(port);
			System.out.println("Server stated");
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
				//nhan du lieu tu client
				serverSocket.receive(receivePacket);
				//lay dia chi ip cua may client
				IPAddress = receivePacket.getAddress();
				// lay port cua client
				port = receivePacket.getPort();
				//lay ngay gio
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
				sendData = ("Server: "+ outputStr).getBytes();
				if(IPAddress != null && port != -1) {
					DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,port);
					//gui dl lai cho client
					serverSocket.send(sendPacket);
					keyboard = keyboard.reset();
				}
			}
		}
		catch(IOException e) {
			e.getMessage();
		}
	}

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
//		DatagramSocket serverSocket = new DatagramSocket(9876);
//		System.out.println("Server is started");
//		//tao mang byte de chua du lieu nhan va gui
//		byte[] receiveData = new byte[1024];
//		byte[] sendData = new byte[1024];
//		
//		Scanner sc = new Scanner(System.in);
//		while(true) {
//			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//			//nhan du lieu tu client
//			serverSocket.receive(receivePacket);
//			//lay dia chi ip cua may client
//			InetAddress IPAddress = receivePacket.getAddress();
//			// lay port cua client
//			int port = receivePacket.getPort();
//			//lay ngay gio
//			String request = new String(receivePacket.getData());
//			System.out.println(request);
//			System.out.println("Server: ");
//			String msg = sc.nextLine();
//			sendData = ("Server: "+msg).getBytes();
//			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,port);
//			//gui dl lai cho client
//			serverSocket.send(sendPacket);
//			sc = sc.reset();
//		}
		new UDPChatServer(9876);
	}

}
