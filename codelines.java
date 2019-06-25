// Ansammlung von relevanten code lines für das Webradio
// KEIN ausführbares java file
// Sollte euch als kleine Inspiration helfen

ServerSocket ss = new ServerSocket(port);
Socket client = ss.accept();

// So lasst ihr in Java Code als separaten Thread ausführen
public class <RunnableClassName> implements Runnable {
  ...
  public void run(){
  // müsst ihr selbst implementieren
  ...
  }

}
Thread t = new Thread(<RunnableObject>);
t.start() // starte Thread. Thread führt run() Methode aus.

File soundfile = new File(song);
// song ist ein String und enthält den Pfad des Songs

// .wav Dateien können problemlos abgespielt werden.
// Mit .mp3 Dateien zum Beispiel werdet ihr Probleme haben.

// So sendet und empfangt ihr generell Daten mit Sockets
InputStream in = socket.getInputStream(); // zu empfangende Daten
OutputStream out = socket.getOutputStream(); // zu sendende Daten

in.read(buffer, 0, length); // in buffer legt ihr Daten ab
out.write(buffer, 0, length); // Daten in buffer versendet ihr
// length ungefähr 2048, solltet aber selbst mal probieren

// AudioSystem und SourceDataline sind relevante Bibliotheken

// Folgendes macht ihr einmalig auf Seiten des Clients
InputStream = socket.getInputStream();
AudioInputStream ais = AudioSystem.getAudioInputStream(in);
AudioFormat format = ais.getFormat();
SourceDataline sourceline = AudioSystem.getSourceDataline(format);

sourceline.open();
sourceline.start();

// Folgendes müsst ihr in einer Schleife mehrmals ausführen

ais.read(buffer, 0, length);
sourceline.write(buffer, 0 , length);// spielt quasi die Musik aus dem Buffer ab
