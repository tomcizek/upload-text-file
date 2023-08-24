package org.example.client;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class FileSender {
  public static final String SERVER_ADDRESS = "localhost";
  public static final int SERVER_PORT = 8080;

  public static void sendFileToServer(String resourcePath) {
    try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
         BufferedReader fileReader = new BufferedReader(new InputStreamReader(FileSender.class.getResourceAsStream(resourcePath)));
         OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream())) {

      String line;
      while ((line = fileReader.readLine()) != null) {
        writer.write(line + "\n");
        writer.flush();
      }

      System.out.println("File sent to server.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
