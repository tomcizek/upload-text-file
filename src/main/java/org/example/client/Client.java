package org.example.client;

public class Client {

  public static void main(String[] args) {
    if (args.length < 1) {
      System.out.println("Please provide the path to the text file as an argument.");
      return;
    }

    String filePath = args[0];

    FileSender.sendFileToServer(filePath);
  }
}