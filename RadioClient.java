import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class RadioClient {
  public static void main (String args[]) throws IOException {
    try {
      
      Socket clientSocket = new Socket("localhost",6789); //waits for client to connect to arg[0] (socket)
      
      while(true){
        byte[] buffer= new byte[2048];
      InputStream in  = clientSocket.getInputStream(); //creates input stream, data server receives
      OutputStream out= clientSocket.getOutputStream(); //creates output stream,data server sends
      
      InputStream stream = new BufferedInputStream(in);
      
      AudioInputStream ais = AudioSystem.getAudioInputStream(stream);
      AudioFormat format = ais.getFormat();
      SourceDataLine sourceline = AudioSystem.getSourceDataLine(format);
      
      sourceline.open();
      sourceline.start();
        while (ais.read(buffer, 0, 2048) != -1) {
          sourceline.write(buffer, 0 , 2048);// spielt quasi die Musik aus dem Buffer ab
        }
        if(ais.read(buffer, 0, 2048) != -1) break;
      }

      clientSocket.close();	// ready to accept new client

    } catch (IOException e) {
      System.out.println(e);
    } catch (Exception e) {
      System.out.print(e);
    }
  }
}
