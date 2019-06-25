import java.net.*;
import java.io.*;
public class Live_Chat {
	public static void main (String args[]) throws IOException {
		ServerSocket listen = new ServerSocket(6789);
		while(true) { // non-terminating server
			Socket socket = listen.accept();
			BufferedReader in = new BufferedReader(
			new InputStreamReader(
			socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			while (true) { // end of input stream will close dialogue
				String message = in.readLine();
				if(message==null) {
					System.out.println("Socket closed.");
					break;
				}
				String answer = message.replace('i','o');
				out.println(answer);
			}
			in.close();
			out.close();
			listen.close();
			socket.close(); // close connection
		}
	}
}