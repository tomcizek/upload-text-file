package org.example.server;

import java.sql.ResultSet;
import java.sql.Statement;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
public class TextLineRepositoryTest {

  private TextLineRepository textLineRepository;

  @Before
  public void setUp() throws Exception {
    textLineRepository = new TextLineRepository();

    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate("DELETE FROM text_lines");
    }
  }

  @Test
  public void itShouldSaveLineToDatabase() throws SQLException {
    String testLine = "Test line content";
    textLineRepository.saveLineToDatabase(testLine);

    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement()) {
      ResultSet resultSet = statement.executeQuery("SELECT line_content FROM text_lines WHERE line_content = '" + testLine + "'");
      assertTrue(resultSet.next());
      assertEquals(testLine, resultSet.getString("line_content"));
    }
  }

}