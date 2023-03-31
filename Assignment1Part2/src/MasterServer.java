import java.net.*;
import java.util.Scanner;
import java.io.*;

public class MasterServer{    

    public static void main(String args[]) {  
    	//  args[0] = server1 IP address

        DatagramSocket aSocket=null;
    	try {
            InetAddress serverIP = InetAddress.getByName(args[0]);
            
            //Slave 1
		    Scanner scanner = new Scanner(System.in);
		    System.out.println("Enter the value of slave 1: ");
		    int slave1 = scanner.nextInt();
            aSocket = new DatagramSocket();
            String msg1 = slave1+"";
            int server1Port = 20001;
            DatagramPacket request = new DatagramPacket(msg1.getBytes(), msg1.length(), serverIP, server1Port); 
            aSocket.send(request);
            
            //Slave 2
            Scanner scanner1 = new Scanner(System.in);
		    System.out.println("Enter the value of slave 2: ");
		    int slave2 = scanner.nextInt();
            String msg2 = slave2+"";
            int server2Port = 20002;
            DatagramPacket request1 = new DatagramPacket(msg2.getBytes(), msg2.length(), serverIP, server2Port); 
            aSocket.send(request1);	
            
            //Slave 3
            Scanner scanner2 = new Scanner(System.in);
		    System.out.println("Enter the value of slave 3: ");
		    int slave3 = scanner.nextInt();
            String msg3 = slave3+"";
            int server3Port = 20003;
            DatagramPacket request2 = new DatagramPacket(msg3.getBytes(), msg3.length(), serverIP, server3Port); 
            aSocket.send(request2);	
            
            //Slave 4
            Scanner scanner3 = new Scanner(System.in);
		    System.out.println("Enter the value of slave 4: ");
		    int slave4 = scanner.nextInt();
            String msg4 = slave4+"";
            int server4Port = 20004;
            DatagramPacket request3 = new DatagramPacket(msg4.getBytes(), msg4.length(), serverIP, server4Port); 
            aSocket.send(request3);	
            
            
            byte[] buffer = new byte[1000];
            DatagramPacket reply = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply);		
            System.out.println("Received PrimeNumbers from Slave 1: " + new String(reply.getData(), 0, reply.getLength()));
            
            DatagramPacket reply1 = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply1);		
            System.out.println("Received PrimeNumbers from Slave 2: " + new String(reply1.getData(), 0, reply1.getLength()));
            
            DatagramPacket reply2 = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply2);		
            System.out.println("Received PrimeNumbers from Slave 3: " + new String(reply2.getData(), 0, reply2.getLength()));
            
            DatagramPacket reply3 = new DatagramPacket(buffer,buffer.length);
            aSocket.receive(reply3);		
            System.out.println("Received PrimeNumbers from Slave 4: " + new String(reply3.getData(), 0, reply3.getLength()));
            
            
        }catch (SocketException e){System.out.println("Error Socket: " + e.getMessage());
        }catch (IOException e){System.out.println("Error IO: " + e.getMessage());
        }finally { 
            if(aSocket != null) aSocket.close();
        }
    }
}

                       