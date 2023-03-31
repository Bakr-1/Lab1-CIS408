
import java.net.*;
import java.io.*;

public class Slave{    
	
	
	public static String prime(int n) {
		  int i =0;
	      int num =0;

	      //Empty String
	      String  primeNumbers = "";
	      for (i = 1; i <= n; i++)  	   
	      {
	         int counter=0; 		  
	         for(num =i; num>=1; num--)
	         {
		    if(i%num==0)
		    {
			counter = counter + 1;
		    }
		 }
		 if (counter ==2)
		 {
		    //Appended the Prime number to the String
		    primeNumbers = primeNumbers + i + " ";
		 }	
	      }	
	     return primeNumbers;
	   }


   public static void main(String args[]) {     
   	//  args[0] = server ID
   	//  args[1] = IP of server 2
	   
       DatagramSocket aSocket=null;
       InetAddress destinationIP = null;
    try{	    	
    	String msg1="";
    	int serverID = Integer.parseInt(args[0]);
        aSocket = new DatagramSocket(20000 + serverID);
    	String msg="";
        
	    byte[] buffer = new byte[1000]; 			
	   	System.out.println("Slave " + serverID + " is running ");
		while(true){ 				
			 DatagramPacket request = new DatagramPacket(buffer,buffer.length);
			 aSocket.receive(request);
			 destinationIP = InetAddress.getByName(args[1]);
			 int destinationPort =request.getPort(); 

			if (serverID==1){

			    msg1 =new String(request.getData(), 0, request.getLength()); 
			    int PS= Integer.parseInt(msg1);
			    msg=prime(PS);
				DatagramPacket request1 = new DatagramPacket(msg.getBytes(), msg.length(), destinationIP, destinationPort); 
				aSocket.send(request1);
	
				}
			if (serverID==2){
			    msg1 =new String(request.getData(), 0, request.getLength()); 
			    int PS= Integer.parseInt(msg1);
			    msg=prime(PS);
				DatagramPacket request1 = new DatagramPacket(msg.getBytes(), msg.length(), destinationIP, destinationPort); 
				aSocket.send(request1);
			}
			if (serverID==3){
			    msg1 =new String(request.getData(), 0, request.getLength()); 
			    int PS= Integer.parseInt(msg1);
			    msg=prime(PS);
				DatagramPacket request1 = new DatagramPacket(msg.getBytes(), msg.length(), destinationIP, destinationPort); 
				aSocket.send(request1);
			}
			if (serverID==4){
			    msg1 =new String(request.getData(), 0, request.getLength()); 
			    int PS= Integer.parseInt(msg1);
			    msg=prime(PS);
				DatagramPacket request1 = new DatagramPacket(msg.getBytes(), msg.length(), destinationIP, destinationPort); 
				aSocket.send(request1);
			}

		}

	
		
 	}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
	}finally {
		if(aSocket != null) aSocket.close();
	}
   }
}
