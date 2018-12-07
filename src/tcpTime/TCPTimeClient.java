package tcpTime;

import java.util.*;
import java.net.*;
import java.io.*;

public class TCPTimeClient {
	public TCPTimeClient(String host, int port) throws Exception {
		Socket sk = new Socket(host, port);
		DataInputStream din = new DataInputStream(sk.getInputStream());
		String time = din.readUTF();
		System.out.println(time);
	}
	public static void main(String[] args) {
		try {
			new TCPTimeClient("localhost", 5000);
		}
		catch(Exception e) {
			e.getMessage();
		}
	}

}
