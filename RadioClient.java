import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class RadioClient {
  public static void main (String args[]) throws IOException {
    try {
      Socket clientSocket = new Socket("localhost",6789); //waits for client to connect to arg[0] (socket)
      
      while(true){
        int length = 2048;
        byte[] buffer= new byte[length];
        InputStream in  = clientSocket.getInputStream(); //creates input stream, data server receives
        
        InputStream stream = new BufferedInputStream(in);
        
        AudioInputStream ais = AudioSystem.getAudioInputStream(stream);
        AudioFormat format = ais.getFormat();
        SourceDataLine sourceline = AudioSystem.getSourceDataLine(format);
        
        sourceline.open();
        sourceline.start();
        while (ais.read(buffer, 0, length) != -1) {
          sourceline.write(buffer, 0 , length);// spielt quasi die Musik aus dem Buffer ab
        }
        if(ais.read(buffer, 0, length) != -1) break;
      }
      clientSocket.close();	
    } catch (IOException e) {
      System.out.println(e);
    } catch (Exception e) {
      System.out.print(e);
    }
  }
}
