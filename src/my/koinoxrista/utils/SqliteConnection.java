package my.koinoxrista.utils;

import java.io.File;
import java.sql.*;
import javax.swing.*;

public class SqliteConnection {

    Connection conn = null;

    public static Connection getConnection() {
        Connection conn = null;
        String homeDirectory = System.getProperty("user.home");
        String separator = File.separator;
        String dbFilename = "koinoxrista.db";
        String dbPath = homeDirectory + separator + dbFilename;

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null, "Connection Failed. The error is: " + e.getMessage());
        }
        return conn;
    }
}
