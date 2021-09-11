package br.paulista.nelson.paokentin.model.repositorios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
  private static final String URL = "jdbc:mysql://localhost:3306/paokentin";
  private static final String USER = "root";
  private static final String PASSWORD = "1234";
  private static Connection connection = null;
  
  static Connection getConnection() throws SQLException {
    if (connection == null) {
      connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }
    return connection;
  }
}
