import java.net.*;
import java.util.Random;
import java.io.*;

public class UDPClientPart3{    

    public static void main(String args[]) {  
  //   args[0] = message to be sent to the server; 
  //   args[1] = IP address of the server 
  //   args[2] = Port of the server 1 
  //   args[3] = Port of the server 2
    	 
        DatagramSocket aSocket=null;
    	try {
    		   aSocket = new DatagramSocket();
               byte [] m = args[0].getBytes();
               InetAddress Server1Address = InetAddress.getByName(args[1]);
               int ServerPort1 = Integer.parseInt(args[2]);
               InetAddress Server2Address = InetAddress.getByName(args[1]);
               int ServerPort2 = Integer.parseInt(args[3]);
               DatagramPacket request = new DatagramPacket(m, m.length, Server1Address, ServerPort1); 
               aSocket.send(request);			                        
               byte[] buffer = new byte[1000];
               DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
               aSocket.receive(reply);		
               
               String msg =  new String(reply.getData(), 0, reply.getLength());
    		   request = new DatagramPacket(msg.getBytes(), msg.length(), Server2Address, ServerPort2);		   
               aSocket.send(request);			                        
               aSocket.receive(reply);		

               System.out.println("Received Reply: " + new String(reply.getData(), 0, reply.getLength()));

        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
        }finally { 
            if(aSocket != null) aSocket.close();
        }
    }
}

                       