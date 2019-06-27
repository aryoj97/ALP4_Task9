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
    public void run() {
      try {
        File soundfile = new File("./sound.wav");
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundfile);

        InputStream in = socket.getInputStream(); //creates input stream, data server receives
        OutputStream out= socket.getOutputStream(); //creates output stream,data server sends
        FileInputStream stream = new FileInputStream(soundfile);

        byte[] buffer= new byte[4096];

        Clip clip = AudioSystem.getClip();
        clip.open(ais);
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        Thread.sleep(10000); //looping as long as this thread is alive

        stream.close(); out.close();	// ready to accept new client
      } catch (IOException e) {
        System.out.println(e);
      } catch (Exception e) {
        System.out.print(e);
      }
    }
  }
}
