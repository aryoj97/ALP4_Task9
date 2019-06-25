import java.io.*;
import java.net.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;
class MakeSound { // https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java
	int BUFFER_SIZE = 128000;
	File soundFile;
	AudioInputStream audioStream;
	AudioFormat audioFormat;
	SourceDataLine sourceLine;

	/**
	 * @param filename the name of the file that is going to be played
	 */
	public void playSound(String filename){
			String strFilename = filename;
			try {
					soundFile = new File(strFilename);
			} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
			}
			try {
					audioStream = AudioSystem.getAudioInputStream(soundFile);
			} catch (Exception e){
					e.printStackTrace();
					System.exit(1);
			}
			audioFormat = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
			try {
					sourceLine = (SourceDataLine) AudioSystem.getLine(info);
					sourceLine.open(audioFormat);
			} catch (LineUnavailableException e) {
					e.printStackTrace();
					System.exit(1);
			} catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
			}
			sourceLine.start();
			int nBytesRead = 0;
			byte[] abData = new byte[BUFFER_SIZE];
			while (nBytesRead != -1) {
					try {
							nBytesRead = audioStream.read(abData, 0, abData.length);
					} catch (IOException e) {
							e.printStackTrace();
					}
					if (nBytesRead >= 0) {
							@SuppressWarnings("unused")
							int nBytesWritten = sourceLine.write(abData, 0, nBytesRead);
					}
			}

			sourceLine.drain();
			sourceLine.close();
	}
}
public class Live_Chat_client {
	public static void main (String args[]){ //args[0] host name and args[1] port
		// Socket socket = new Socket("localhost", 6789);
		// PrintStream out = new PrintStream(socket.getOutputStream());
		// BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //BufferedReader reads Keyboard Input
		// BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
		// while(true) { // ^D will close dialogue
		// 	String message = keyboard.readLine();
		// 	if(message==null) break;
		// 	out.println(message);
		// 	String answer = in.readLine();
		// 	System.out.println(answer);
		// }
		// in.close();
		// out.close();
		// socket.close();
		// String soundfile = "/home/aryo/Desktop/Uni ALP/ALP4_Task9/sound.wav";
		MakeSound audio = new MakeSound();
		audio.playSound("./sound.wav");
		// playSound(soundfile);
	}
}