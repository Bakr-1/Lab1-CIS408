import java.net.*;
import java.util.Hashtable;
import java.io.*;
import java.util.*;
import java.text.*;

public class UDPServerPart3{    
   public static void main(String args[]) {     
	   // args[0] = server ID
	   
       DatagramSocket aSocket=null; 
    try{	    	
        int serverID =  Integer.parseInt(args[0]);
        aSocket = new DatagramSocket(20000+serverID);
	    byte[] buffer = new byte[1000]; 			
	   	System.out.println("Server "+ serverID+ " is ready and accepting clients' requests ... ");
	   	while(true){ 				
			DatagramPacket request = new DatagramPacket(buffer,buffer.length);
			aSocket.receive(request);
			String msg =new String(request.getData(),0,request.getLength());
			System.out.println(msg + request.getAddress() +request.getPort());
			 Date dNow = new Date( );
		     SimpleDateFormat ft = 
		     new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

			msg += " Server "+serverID+": " + ft.format(dNow);
			
			DatagramPacket reply = new DatagramPacket(msg.getBytes(),
					msg.length(),
					request.getAddress(),
					request.getPort());
					aSocket.send(reply);
		}		
 	}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
	}finally {
		if(aSocket != null) aSocket.close();
	}
   }
}
