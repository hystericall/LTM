package tcpString;

import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class Server {
	//initialize socket and input stream 
    private Socket          socket   = null; 
    private ServerSocket    server   = null; 
    private DataInputStream in       =  null;
    private DataOutputStream out     = null;
  
    // constructor with port 
    public Server(int port) 
    { 
        // starts server and waits for a connection 
        try
        { 
            server = new ServerSocket(port); 
            System.out.println("Server started"); 
  
            System.out.println("Waiting for a client ..."); 
  
            socket = server.accept(); 
            System.out.println("Client accepted"); 
  
            // takes input from the client socket 
            in = new DataInputStream( 
                new BufferedInputStream(socket.getInputStream())); 
            // send output to the socket
            out = new DataOutputStream(socket.getOutputStream());
            String line = ""; 
            String[] response = new String[4]; 
  
            // reads message from client until "Over" is sent 
            while (!line.equals("Over")) 
            { 
                try
                { 
                    line = in.readUTF();
                    response[0] = line.toUpperCase();
                    response[1] = convertOpposite(new StringBuffer(line));
                    response[2] = timNguyenAm(response[0]);
                    response[3] = String.valueOf(line.split(" ").length);
                    for(String i : response) {
                    	out.writeUTF(i);
                    }
                    out.writeUTF("Over");
                } 
                catch(IOException i) 
                { 
                    System.out.println(i); 
                } 
            } 
            System.out.println("Closing connection"); 
  
            // close connection 
            socket.close(); 
            in.close(); 
        } 
        catch(IOException i) 
        { 
            System.out.println(i.getMessage()); 
        } 
    } 
    
    public String convertOpposite(StringBuffer string) 
    { 
       int ln = string.length(); 
       StringBuffer str = string;
       // Conversion using predefined methods 
       for (int i=0; i<ln; i++) 
       { 
           Character c = str.charAt(i); 
           if (Character.isLowerCase(c)) 
               str.replace(i, i+1, Character.toUpperCase(c)+""); 
           else
               str.replace(i, i+1, Character.toLowerCase(c)+""); 
       }
       return str.toString();
    }
    
    public static String timNguyenAm(String str) {
    	List<String> rs = new ArrayList<String>();
    	String[] chs = {"A", "E", "I", "O", "U"};
    	for (int i = 0; i < str.length(); i++) {
    		String ch = String.valueOf(str.charAt(i));
    		for(int j = 0; j < 5; j++) {
    			if(ch.equals(chs[j])) {
    				if(!rs.contains(chs[j])) {
    					rs.add(chs[j]);
    				}
    			}
    		}
		}
    	str = String.join(",", rs);
    	return str;
    }
    
    public static void main(String args[]) 
    { 
//    	System.out.println(Server.timNguyenAm("ASDFASBEDFASDF"));
        Server server = new Server(5000);
    }
}
