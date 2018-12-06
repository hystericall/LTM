package simpleChat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
	public static void main(String[] args) throws Exception {
		ServerSocket server = new ServerSocket(7000);
		System.out.println("Server stated");
		Socket sk = server.accept();
		DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
		DataInputStream din = new DataInputStream(sk.getInputStream());
		Scanner kb = new Scanner(System.in);
		while(true) {
			String st = din.readUTF();
			System.out.println(st);
			System.out.println("Server: ");
			String msg = kb.nextLine();
			dos.writeUTF("Server: " + msg);
			dos.flush();
			kb = kb.reset();
		}
		
	}
}
