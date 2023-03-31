
import java.net.*;
import java.util.Scanner;
import java.io.*;

public class ServersCom{    
	   public static void main(String args[]) {  
		   //args[0] = server ID
		   //args[1] = IP
		   //args[2] = NextPort


		   DatagramSocket mySocket = null;
		   InetAddress destinationIP = null;
	    try{	 
	    	String msg1="";
	    	int serverID = Integer.parseInt(args[0]);
	    	mySocket = new DatagramSocket(20000 + serverID);
	        destinationIP = InetAddress.getByName(args[1]);
		   	System.out.println("Server "+ serverID+ " is waiting...");
	       
	       int rounds =-1;
	       if (serverID == 1){
			   Scanner input = new Scanner(System.in);
			   System.out.println("Enter how many rounds: ");
			   rounds = input.nextInt();
			}
			while(rounds!=0){
			    byte[] buffer = new byte[1000];
	            Thread.sleep(1000);
		    	int NextPort = Integer.parseInt(args[2]);


				  if (serverID == 1) {
	            	 msg1 = msg1+"1";		
	            	 DatagramPacket request1 = new DatagramPacket(msg1.getBytes(), msg1.length(), destinationIP, NextPort); 
	            	 mySocket.send(request1);
	            	 DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
	          		 System.out.println(msg1);
	                 mySocket.receive(reply);
	                 msg1 =new String(reply.getData(), 0, reply.getLength());
	             }
	             
	              if (serverID == 2) {
	            	  DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
	                  mySocket.receive(reply);
	            	 String msg2 = new String(reply.getData(), 0, reply.getLength()) + "2";
	           		 System.out.println(msg2);
	            	 DatagramPacket request2 = new DatagramPacket(msg2.getBytes(), msg2.length(), destinationIP, NextPort); 
	            	 mySocket.send(request2); 	
	             }
	              if (serverID == 3){
	            	  DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
	                  mySocket.receive(reply);
	             	 String msg3 = new String(reply.getData(), 0, reply.getLength()) +"3";
	           		 System.out.println(msg3);
	            	 DatagramPacket request3 = new DatagramPacket(msg3.getBytes(), msg3.length(), destinationIP, NextPort); 
	            	 mySocket.send(request3);
	            }
	              if(rounds==1)
	              {
	           		 System.out.println(msg1);
	              }
	              
	              rounds--;
	           }
			 

				
	 	}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
	 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(mySocket != null) mySocket.close();
		}
	   }
	}