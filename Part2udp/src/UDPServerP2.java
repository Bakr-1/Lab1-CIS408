import java.net.*;
import java.util.Hashtable;
import java.io.*;

public class UDPServerP2{    
	   public static void main(String args[]) {     	
		   DatagramSocket aSocket = null;
	    try{	    	
	    	aSocket = new DatagramSocket(20000); 
		    byte[] buffer = new byte[1000]; 			
		   	System.out.println("Server is ready and accepting clients' requests ... ");
			Hashtable<String, Integer> clients = new Hashtable();
			
		   	while(true){ 				
			     DatagramPacket request = new DatagramPacket(buffer, buffer.length);
	             aSocket.receive(request); 
	             String msg = new String(request.getData(), 0, request.getLength());
	             System.out.println(msg + request.getAddress() + "/" + request.getPort());
	          String clientID =  "" + request.getAddress() + request.getPort();
	             Integer clientSum = clients.get(clientID);
	             if (clientSum == null) {
	            	 clients.put(clientID, 0);
	            	 clientSum = 0;
	                  }
	             clientSum  += Integer.parseInt(msg);
	             clients.put(clientID, clientSum);
	             msg =  "" + clientSum;
	             DatagramPacket reply = new DatagramPacket(
	            		   request.getData(), 
				           request.getLength(),
	   			           request.getAddress(),
				           request.getPort());
	aSocket.send(reply);}

				
	 	}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
	 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
		}finally {
			if(aSocket != null) aSocket.close();
		}
	   }
	}
