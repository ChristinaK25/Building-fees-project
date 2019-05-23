package my.koinoxrista.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import my.koinoxrista.utils.SqliteConnection;

public class Apartment {

    int id;
    int buildingId;
    int floor;
    String code;
    String ownerName;
    int surface;
    String ownerPhone;
    float perMille;
    String type;

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

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getSurface() {
        return surface;
    }

    public void setSurface(int surface) {
        this.surface = surface;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public float getPerMille() {
        return perMille;
    }

    public void setPerMille(float perMille) {
        this.perMille = perMille;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static String getTypeLiteral(String type) {
        if (type.equals("apartment")) {
            return "Διαμέρισμα";
        } else if (type.equals("store")) {
            return "Κατάστημα";
        }
        return "";
    }

    public static Apartment getById(int id) {
        String query = "select * from apartments where id = ?";
        Apartment apartment = new Apartment();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = SqliteConnection.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                apartment.setId(rs.getInt("id"));
                apartment.setBuildingId(rs.getInt("building_id"));
                apartment.setFloor(rs.getInt("floor"));
                apartment.setCode(rs.getString("code"));
                apartment.setOwnerName(rs.getString("owner_name"));
                apartment.setSurface(rs.getInt("surface"));
                apartment.setOwnerPhone(rs.getString("owner_phone"));
                apartment.setPerMille(rs.getFloat("per_mille"));
                apartment.setType(rs.getString("type"));
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

        return apartment;
    }
}
