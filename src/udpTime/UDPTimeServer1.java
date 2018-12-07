package udpTime;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPTimeServer1 {
	public static void main(String[] args) throws Exception {
		//Gan cong 9876 cho chuong trinh
		DatagramSocket serverSocket = new DatagramSocket(9876);
		//Tao cac mang byte de chua dl gui va nhan
		System.out.println("Server is started");
		byte[] receivedData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true) {
			//Tao goi rong de nhan dl tu client
			DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
			//Nhan du lieu tu client
			serverSocket.receive(receivePacket);
			//Lay dia chi IP cua may client
			InetAddress IPAddress = receivePacket.getAddress();
			// Lay port cua ct client
			int port = receivePacket.getPort();
			// Lay ngay gio de gui ngc lai client
			String request = new String(receivePacket.getData());
			System.out.println(request);
			if(request.trim().equals("getDate"))
				sendData = new Date().toString().getBytes();
			else sendData = "Server not know wut chu want?".getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//Gui di lai cho client
			serverSocket.send(sendPacket);
		}
	}
}
