package simpleDate;

import java.util.*;
import java.net.*;
import java.io.*;

public class Client {
	public Client(String host, int port) throws Exception {
		Socket sk = new Socket(host, port);
		DataInputStream din = new DataInputStream(sk.getInputStream());
		String time = din.readUTF();
		System.out.println(time);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
