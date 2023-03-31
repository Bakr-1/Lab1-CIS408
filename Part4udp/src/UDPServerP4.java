import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;

public class UDPServerP4{    
	   public static void main(String args[]) {     
		   	//  args[0] = server ID
		   	//  args[1] = IP of server 
			   
		       DatagramSocket aSocket=null;
		       InetAddress destinationIP = null;
		       int destinationPort =0; 
		    try{	    	
		    	int serverID = Integer.parseInt(args[0]);
		        aSocket = new DatagramSocket(20000 + serverID);
			    byte[] buffer = new byte[1000]; 			
			   	System.out.println("Server " + serverID + " is ready and accepting clients' requests ... ");
				while(true){ 				
					DatagramPacket request = new DatagramPacket(buffer,buffer.length);
					aSocket.receive(request);
					String msg =new String(request.getData(),0,request.getLength());
					 Date dNow = new Date( );
				     SimpleDateFormat ft = 
				     new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
					String time = "Server "+serverID+": " + ft.format(dNow);
					if (serverID==1){
						msg = time +","+ request.getAddress()+","+request.getPort();
						destinationIP = InetAddress.getByName(args[1]);
						destinationPort = 20002;
					} else{
						String [] array =msg.split(",");
						msg = array[0] + ","+time;
						destinationIP = InetAddress.getByName(array[1].substring(1));
						destinationPort = Integer.parseInt(array[2]);
					}
					DatagramPacket reply = new DatagramPacket(msg.getBytes(),
							msg.length(),
							destinationIP,
							destinationPort);
							aSocket.send(reply);
				}		
		 	}catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
		 	}catch (IOException e) {System.out.println("Error IO: " + e.getMessage());
			}finally {
				if(aSocket != null) aSocket.close();
			}
		   }
		}
