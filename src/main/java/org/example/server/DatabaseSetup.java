package org.example.server;

import java.sql.Connection;
import java.sql.Statement;

public class DatabaseSetup {

  private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS text_lines (" +
      "id SERIAL PRIMARY KEY, " +
      "line_content TEXT NOT NULL" +
      ");";

  public static void ensureSchemaPreparedExists() {
    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement()) {
      statement.execute(CREATE_TABLE_SQL);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("Error setting up database schema.");
    }
  }
}
