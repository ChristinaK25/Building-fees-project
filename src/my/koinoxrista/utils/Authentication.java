package my.koinoxrista.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Authentication {

    public static boolean authenticate(int buildingId, String username, String password) {
        Connection connection = SqliteConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String query = "select count(*) as count from buildings where id = ? and manager_username = ? and manager_password = ?";
            String passwordHash = MD5PasswordHasher.hash(password);
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, buildingId);
            pstmt.setString(2, username);
            pstmt.setString(3, passwordHash);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                if (rs.getInt("count") == 1) {
                    return true;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return false;
    }
}
