package org.example.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

  private static final String DB_URL = "jdbc:postgresql://localhost:3000/postgres";
  private static final String DB_USER = "postgres";
  private static final String DB_PASSWORD = "postgres";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
  }
}