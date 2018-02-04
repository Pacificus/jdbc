package com.hillel.jdbc;

import java.sql.*;

public class JdbcExample {

  public static void main(String[] args) throws SQLException {
    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/school?serverTimezone=UTC&useSSL=false",
        "root", "dima");

    System.out.println("connect");
    prinStudents(connection);

    connection.close();
  }

  public static void  prinStudents(Connection connection) {
    String sql = "select * from students";
    Statement statement = null;
    ResultSet resultSet = null;

    try {
      statement = connection.createStatement();
      resultSet = statement.executeQuery(sql);

      while (resultSet.next()) {
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        System.out.println(firstName + " " + lastName + " " + age);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }  finally {
      try {
        resultSet.close();
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }

    }
  }
}
