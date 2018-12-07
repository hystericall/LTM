package simpleChat;

import java.net.*;
import java.util.*;
import java.io.*;

public class ChatClient {

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket("localhost", 7000);
		System.out.println("Connected");
		DataInputStream din = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		while(true) {
			Scanner kb = new Scanner(System.in);
			String st = din.readUTF();
			System.out.println(st);
			System.out.println("Client: ");
			String msg = kb.nextLine();
			dos.writeUTF("Client: " + msg);
			dos.flush();
			kb = kb.reset();
		}
	}
}
