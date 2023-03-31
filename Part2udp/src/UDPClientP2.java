import java.net.*; 
import java.io.*;
import java.util.Random;

public class UDPClientP2{    


    public static void main(String args[]) {  

    // args[0] = IP address of the server

        DatagramSocket aSocket = null;
        try {
        	aSocket = new DatagramSocket();
            byte [] m = args[0].getBytes();
            InetAddress aHost = InetAddress.getByName(args[0]);
            int serverPort = 20000;
            Random rand = new Random();
            for (int i=1; i<=10; i++){
            	String msg = rand.nextInt(500) + "";
            DatagramPacket request = new DatagramPacket(msg.getBytes(), msg.length(), aHost, serverPort);
        	aSocket.send(request);		                        
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
        	aSocket.receive(reply);		
            System.out.println("Received Reply: " + new String(reply.getData(), 0, reply.getLength()));	
            Thread.sleep(5000);
            }
        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
        } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
            if(aSocket != null) aSocket.close();
        }
    }
}

                       