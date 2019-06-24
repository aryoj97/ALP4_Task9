import java.io.*;  
import java.net.*;  
public class Live_Chat_client {  
	public static void main(String[] args) throws IOException {  
		try {
			String sentence;
			String modifiedSentence;
			BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
			Socket clientSocket = new Socket ("localhost", 6789);
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader (clientSocket.getInputStream()));
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			modifiedSentence = inFromServer.readLine();
			System.out.println(modifiedSentence);
			while(true){
				sentence = inFromUser.readLine();
				outToServer.writeBytes("client: " + sentence + '\n');
			}
		} catch (IOException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.print(e);
		}
	}  
}  