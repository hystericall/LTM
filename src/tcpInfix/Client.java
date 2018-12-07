package tcpInfix;
import java.net.*;
import java.io.*;
	
public class Client {
	private Socket socket = null;
	private BufferedReader input = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	
	public Client(String address, int port) {
		try {
			// Connect to a server
			socket = new Socket(address, port);
			System.out.println("Connected");
			// Get input from terminal
			input = new BufferedReader(new InputStreamReader(System.in));
			// Send output to the socket
			out = new DataOutputStream(socket.getOutputStream());
			// Get input from to socket
			in = new DataInputStream(socket.getInputStream());
		}
		catch(UnknownHostException u) {
			System.out.println(u.getMessage());
		}
		catch(IOException i) {
			System.out.println(i.getMessage());
		}
		
		// String to read message from terminal input
		String line = "";
		// String to get message from the socket
		String response;
		
		while(!line.equals("Over")) {
			try {
				line = input.readLine();
				out.writeUTF(line);
				do {
					response = in.readUTF();
					System.out.println(response);
				} while(!response.equals("Over"));
			}
			catch(IOException i) {
				System.out.println(i.getMessage());
			}
		}
		try {
			socket.close();
			out.close();
//			in.close();
			input.close();
		}
		catch(IOException i) {
			System.out.println(i.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client("127.0.0.1", 5001);
	}
}
