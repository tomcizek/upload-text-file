package org.example.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public static final int PORT = 8080;

  public static void main(String[] args) {
    try (ServerSocket serverSocket = new ServerSocket(PORT)) {
      System.out.println("Server started on port: " + PORT);
      DatabaseSetup.ensureSchemaPreparedExists();
      while (true) {
        try (Socket clientSocket = serverSocket.accept();
             BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

          System.out.println("Client connected: " + clientSocket.getInetAddress());

          String line;
          TextLineRepository textFileService = new TextLineRepository();
          while ((line = reader.readLine()) != null) {
            textFileService.saveLineToDatabase(line);
          }

          System.out.println("File processed and saved to database.");

        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
