//
//  TCPServer.java
//
//  Kurose & Ross
//
import java.io.*;
import java.net.*;

class TCPServer extends Thread{

	public Socket s;
	
    public static void main (String args[]) throws Exception {
		// throws Exception here because don't want to deal
		// with errors in the rest of the code for simplicity.
		// (Note: NOT a good practice).
		
         //Welcome socket  ---- SOCKET 1
		 ServerSocket serverSocket = new ServerSocket(9000);
		 //ServerSocket serverSocket = new ServerSocket(8000);
		 // waits for a new connection. Accepts connection from multiple clients
		 while (true) {
			 System.out.println("Waiting for connection at port 9000.");
             //Connection socket  --- SOCKET 2
			 Socket s = serverSocket.accept();
			 System.out.println("Connection established from " + s.getInetAddress());
			 
			 TCPServer server = new TCPServer(s);
			 server.start();
		 }
    }
    
    public TCPServer (Socket s) {
    	this.s = s;
    }
    
    public void run() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    		String input = br.readLine();
    		
    		DataOutputStream output = new DataOutputStream(s.getOutputStream());
    		
    		while (input.compareTo("tchau") != 0) {
    			output.writeBytes(input.toUpperCase() + "\n");
    			input = br.readLine();
    		}
    		
    		s.close();
    	} catch (Exception e) {e.printStackTrace();}
    }
    
}
