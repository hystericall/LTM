package udpInfix;

import java.util.*;
import java.io.IOException;
import java.net.*;


public class Server {
	
	public static void main(String[] args) throws IOException {
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
			String rs = String.valueOf(EvaluateString.evaluate(request));
			sendData = rs.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//Gui di lai cho client
			serverSocket.send(sendPacket);
		}
	}

}
