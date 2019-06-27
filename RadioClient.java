import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class RadioClient {
    public static void main (String args[]) throws IOException {
      try{
        byte[] buffer= new byte[2048];

        Socket clientSocket = new Socket("localhost",6789); //waits for client to connect to arg[0] (socket)

        InputStream in  = clientSocket.getInputStream(); //creates input stream, data server receives
        OutputStream out= clientSocket.getOutputStream(); //creates output stream,data server sends

        InputStream stream = new BufferedInputStream(in);

        AudioInputStream ais = AudioSystem.getAudioInputStream(stream);
        AudioFormat format = ais.getFormat();
        SourceDataLine sourceline = AudioSystem.getSourceDataLine(format);

          //byte[] buffer= new byte[2048];

        sourceline.open();
        sourceline.start();

        while (ais.read(buffer, 0, 2048) != -1) {
            sourceline.write(buffer, 0 , 2048);// spielt quasi die Musik aus dem Buffer ab
            }

        sourceline.drain();
        sourceline.stop();
        sourceline.close();
        in.close();
        out.close();
        clientSocket.close();	// ready to accept new client

      } catch (IOException e) {
  			System.out.println(e);
  		} catch (Exception e) {
  			System.out.print(e);
  		}
    }
}
