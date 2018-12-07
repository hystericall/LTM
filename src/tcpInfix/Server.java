package tcpInfix;
import java.net.*;
import java.io.*;

public class Server {
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream in = null;
	private DataOutputStream out = null;
	
	public Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("Starting server");
			System.out.println("Waiting for client...");
			socket = server.accept();
			System.out.println("Client accepted");
			 // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            // send output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            String line = "";
            
            try {
            	line = in.readUTF();
            	while(!line.equals("Over")) {
            		line = in.readUTF();
            		out.writeUTF(String.valueOf(EvaluateString.evaluate(line)));
            		out.writeUTF("Over");
            	}
            }
            catch(IOException i) {
            	System.out.println(i.getMessage());
            }
            in.close();
            socket.close();
		}
        catch(IOException i) {
        	System.out.println(i.getMessage());
		}
	}
	public static void main(String[] args) {
		Server server = new Server(5001);
	}

}
