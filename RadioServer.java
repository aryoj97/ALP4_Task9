import java.io.*;
import java.net.*;
import javax.sound.sampled.*;

public class RadioServer{
  public static void main (String args[]) throws IOException{
    ServerSocket listen = new ServerSocket(6789);
    while(true) { // new thread for new client
      Socket s = listen.accept();
      new Service(s).start();
    }
  }
  static class Service extends Thread {
    Socket socket;
    Service(Socket s){
      socket = s;
    }
    @Override
    public void run() {
      try {
        File soundfile = new File("./sound.wav");
        while (true) {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundfile);

        OutputStream out = socket.getOutputStream();

        FileInputStream in = new FileInputStream(soundfile);

        byte buffer[] = new byte[2048];
        int length = 2048;
          while ((in.read(buffer,0,2048)) != -1) {
              out.write(buffer, 0, 2048);
          }
        }


        // out.close();	// ready to accept new client
      } catch (IOException e) {
        System.out.println(e);
      } catch (Exception e) {
        System.out.print(e);
      }
    }
  }
}
