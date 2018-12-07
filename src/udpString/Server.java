package udpString;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

import udpInfix.EvaluateString;

import java.io.*;

public class Server {

    public static String convertOpposite(StringBuffer string) 
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
    
    public static void main(String args[]) throws IOException
    { 
    	String[] response = new String[4];
    	//Gan cong 9876 cho chuong trinh
		DatagramSocket serverSocket = new DatagramSocket(9876);
		//Tao cac mang byte de chua dl gui va nhan
		System.out.println("Server is started");
		byte[] receivedData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true) {
			//Tao goi rong de nhan dl tu client
			DatagramPacket receivePacket = new DatagramPacket(receivedData, receivedData.length);
			//Nhan du lieu tu client
			serverSocket.receive(receivePacket);
			//Lay dia chi IP cua may client
			InetAddress IPAddress = receivePacket.getAddress();
			// Lay port cua ct client
			int port = receivePacket.getPort();
			// Lay ngay gio de gui ngc lai client
			String request = new String(receivePacket.getData());
			System.out.println(request);
	        response[0] = request.toUpperCase();
	        response[1] = convertOpposite(new StringBuffer(request));
	        response[2] = timNguyenAm(response[0]);
	        response[3] = String.valueOf(request.split(" ").length);
	        String rs = String.join("\n", response);
			sendData = rs.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
			//Gui di lai cho client
			serverSocket.send(sendPacket);
		}
    }
}
