import java.net.*; 
import java.io.*;

import java.net.*; 
import java.io.*;

public class UDPClientP4{    

    public static void main(String args[]) {  
    	//  args[0] = message
    	//  args[1] = server IP address

        DatagramSocket aSocket=null;
    	try {
            aSocket = new DatagramSocket();
            byte [] message = args[0].getBytes();
            InetAddress server1IP = InetAddress.getByName(args[1]);
            int server1Port = 20001;
            DatagramPacket request = new DatagramPacket(message, args[0].length(), server1IP, server1Port); 
            aSocket.send(request);		
            
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply);		
            System.out.println("Received: " + new String(reply.getData(), 0, reply.getLength()));
            
        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
        }finally { 
            if(aSocket != null) aSocket.close();
        }
    }
}

                       