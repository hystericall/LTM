package udpString;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		Scanner sc = new Scanner(System.in);
		String request = sc.nextLine();
		sendData = request.getBytes();
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
