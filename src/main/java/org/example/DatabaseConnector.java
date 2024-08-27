package org.example;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.DriverManager;

    public class DatabaseConnector {

        public static void main(String[] args) {
            org.example.DatabaseConnector connector = new org.example.DatabaseConnector();
            connector.selectAllBooks();
        }

        public Connection connect() {
            Connection connection = null;
            try {
                String url = "jdbc:sqlite:C:\\Users\\PXSti\\ctac\\java\\JDBCLab\\Bookstore";
                connection = DriverManager.getConnection(url);
                System.out.println("Successfully connected to the database!");
            } catch (SQLException e) {
                System.out.println("Error connecting to the database.");
                System.out.println(e.getMessage());
            }
            return connection;
        }

        public void selectAllBooks() {
            Connection connection = null;
            Statement stmt = null;
            ResultSet rs = null;
            try {
                connection = this.connect();
                String sql = "SELECT * FROM books";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    System.out.println(rs.getString("title") + "\t" +
                            rs.getString("author") + "\t" +
                            rs.getDouble("price"));
                }
            } catch (SQLException e) {
                System.out.println("Error executing SELECT statement");
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (stmt != null) stmt.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
