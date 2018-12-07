package tcpTime;

import java.io.*;
import java.net.*;
import java.util.*;

public class TCPTimeServer {
	public TCPTimeServer(int port) throws Exception {
		ServerSocket server = new ServerSocket(port);
		System.out.println("Server started");
		while(true) {
			Socket socket = server.accept();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String time = new Date().toString();
			dos.writeUTF(time);
			socket.close();
		}
	}
	
	public static void main(String[] args) {
		try {
			new TCPTimeServer(5000);
		}
		catch(Exception e) {
			e.getMessage();
		}
	}
}
