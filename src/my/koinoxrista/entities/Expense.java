package my.koinoxrista.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import my.koinoxrista.utils.SqliteConnection;

public class Expense {

    int id;
    int buildingId;
    int category;
    String description;
    String period;
    float amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public int getCategory() {
        return category;
    }

    public static String getCategoryLiteral(int category) {
        switch (category) {
            case 0:
                return "Κοινόχρηστα";
            case 1:
                return "Θέρμανση";
            case 2:
                return "Συνιδιοκτησία";
            case 3:
                return "Ανελκυστήρας";
        }

        return "";
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public static float getCategorySum(int buildingId, int category, String period) {
        String query = "select sum(amount) as sum from expenses where building_id = ? and category = ? and period like ?";
        float sum = 0;

        Connection connection = SqliteConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, buildingId);
            pstmt.setInt(2, category);
            pstmt.setString(3, period);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sum = rs.getFloat("sum");
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

        return sum;
    }

    public static float getSum(int buildingId, String period) {
        String query = "select sum(amount) as sum from expenses where building_id = ? and period like ?";
        float sum = 0;

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = SqliteConnection.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, buildingId);
            pstmt.setString(2, period);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                sum = rs.getFloat("sum");
            }
            connection.close();
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

        return sum;
    }
}
