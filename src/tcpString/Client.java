package tcpString;
import java.net.*;
import java.io.*;

public class Client {
	// initialize socket and input output streams 
    private Socket socket            = null; 
    private BufferedReader  input   = null;
    private DataInputStream in       =  null;
    private DataOutputStream out     = null; 
  
    // constructor to put ip address and port 
    public Client(String address, int port) 
    { 
        try
        { 
            socket = new Socket(address, port);  
            System.out.println("Connected"); 
  
            // takes input from terminal 
            input  = new BufferedReader(new InputStreamReader(System.in)); 
            // takes input from the socket
            in = new DataInputStream(socket.getInputStream());
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream()); 
        } 
        catch(UnknownHostException u) 
        { 
            System.out.println(u); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
  
        // string to read message from input 
        String line = "";
        String response = "";
  
        // keep reading until "Over" is input 
        while (!line.equals("Over")) 
        { 
            try
            { 
                line = input.readLine(); 
                out.writeUTF(line);
                do {
                	response = in.readUTF();
                	System.out.println(response);
                } while(!response.equals("Over"));
            } 
            catch(IOException i) 
            { 
                System.out.println(i.getMessage()); 
            }
        } 
  
        // close the connection 
        try
        { 
            input.close(); 
            out.close(); 
            socket.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i); 
        } 
    } 
  
    public static void main(String args[]) 
    { 
        Client client = new Client("127.0.0.1", 5000); 
    } 
}
