import java.io.*;
import java.net.*;
public class Live_Chat_client {
public static void main (String args[]) throws IOException{ //args[0] host name and args[1] port
	Socket socket = new Socket("localhost", 6789);
	PrintStream out = new PrintStream(socket.getOutputStream());
	BufferedReader in = new BufferedReader(
	new InputStreamReader(socket.getInputStream())); //BufferedReader reads Keyboard Input
	BufferedReader keyboard = new BufferedReader(
	new InputStreamReader(System.in));
	while(true) { // ^D will close dialogue
		String message = keyboard.readLine();
		if(message==null) break;
		out.println(message);
		String answer = in.readLine();
		System.out.println(answer);
	}
	in.close();
	out.close();
	socket.close();
	}
}