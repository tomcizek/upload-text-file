package org.example.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TextLineRepository {

  private static final String INSERT_QUERY = "INSERT INTO text_lines(line_content) VALUES(?)";

  public void saveLineToDatabase(String line) throws SQLException {
    try (Connection connection = DatabaseConnector.getConnection();
         PreparedStatement statement = connection.prepareStatement(INSERT_QUERY)) {
      statement.setString(1, line);
      statement.executeUpdate();
    }
  }
}