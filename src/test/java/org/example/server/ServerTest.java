package org.example.server;

import static org.junit.Assert.*;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import org.example.client.FileSender;
import org.junit.Before;
import org.junit.Test;

public class ServerTest {

  public static final String TEST_TEXT_FILE_1 = "/test_text_file.txt";

  @Before
  public void setUp() throws Exception {
    new Thread(() -> Server.main(new String[]{})).start();

    try (Connection connection = DatabaseConnector.getConnection();
         Statement statement = connection.createStatement()) {
      statement.executeUpdate("DELETE FROM text_lines");
    }
  }

  @Test
  public void itShouldBeAbleToSendSmallFile() throws Exception {
    
    FileSender.sendFileToServer(TEST_TEXT_FILE_1);
    Thread.sleep(1000);
    try (
        Connection connection = DatabaseConnector.getConnection();
        Statement statement = connection.createStatement()
    ) {
      ResultSet resultSet = statement.executeQuery("SELECT line_content FROM text_lines");
      assertTrue(resultSet.next());
      assertEquals("This is test line 1", resultSet.getString("line_content"));
      assertTrue(resultSet.next());
      assertEquals("This is test line 2", resultSet.getString("line_content"));
      assertFalse(resultSet.next()); 
    }
  }

}

