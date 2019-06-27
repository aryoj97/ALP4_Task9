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
        int length = 2048;
        while (true) {
          OutputStream out = socket.getOutputStream();

          FileInputStream in = new FileInputStream(soundfile);

          byte buffer[] = new byte[length];
          while ((in.read(buffer,0,length)) != -1) {
              out.write(buffer, 0, length);
          }
        }
      } catch (IOException e) {
        System.out.println(e);
      } catch (Exception e) {
        System.out.print(e);
      }
    }
  }
}
