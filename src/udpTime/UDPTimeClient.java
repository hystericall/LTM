package udpTime;

import java.io.*;
import java.net.*;
import java.util.*;

public class UDPTimeClient {
	
	public static void main(String[] args) throws Exception {
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		sendData = "getDate".getBytes();
		// tao datagram co noi dung yeu cau loai dl  de gui cho server
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
		clientSocket.send(sendPacket); // gui dl cho server
		// tao diagram rong de nhan dl
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		// nhan dl tu server
		clientSocket.receive(receivePacket);
		// lay dl tu packet nhan dc
		String str = new String(receivePacket.getData());
		System.out.println(str);
		clientSocket.close();
		
	}

}
