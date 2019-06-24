import java.net.*;  
import java.io.*;
public class Live_Chat { 
	public static void main(String[] args) throws IOException{ 
		try {
			String serverSentence;
			String messageFromClient;
			ServerSocket welcomeSocket = new ServerSocket(6789);
			Socket connectionSocket = welcomeSocket.accept();
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			serverSentence = "Hello World";
			outToClient.writeBytes("server: " + serverSentence + '\n');
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			while(!(messageFromClient = inFromClient.readLine()).equals("end")) {
				System.out.println(messageFromClient);
			}
			welcomeSocket.close();
		} catch (IOException e) {
			System.out.println("User signed off");
		}
	} 
} 