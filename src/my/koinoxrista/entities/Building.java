package my.koinoxrista.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import my.koinoxrista.utils.SqliteConnection;

public class Building {

    int id;
    String address;
    String city;
    String zipCode;
    String managerName;
    String managerPhone;
    String managerUsername;
    String managerPassword;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public String getManagerUsername() {
        return managerUsername;
    }

    public void setManagerUsername(String managerUsername) {
        this.managerUsername = managerUsername;
    }

    public String getManagerPassword() {
        return managerPassword;
    }

    public void setManagerPassword(String managerPassword) {
        this.managerPassword = managerPassword;
    }

    public String getDisplayName() {
        return "Πολυκατοικία " + this.id + " - " + this.address;
    }

    public static Building getById(int id) {
        String query = "select * from buildings where id = ?";
        Building building = new Building();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            connection = SqliteConnection.getConnection();
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                building.setId(rs.getInt("id"));
                building.setAddress(rs.getString("address"));
                building.setCity(rs.getString("city"));
                building.setZipCode(rs.getString("zip_code"));
                building.setManagerName(rs.getString("manager_name"));
                building.setManagerPhone(rs.getString("manager_phone"));
                building.setManagerUsername(rs.getString("manager_username"));
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

        return building;
    }

    public static ArrayList<Expense> getExpenses(int buildingId) {
        Connection connection = SqliteConnection.getConnection();

        String query = "select * from expenses where building_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, buildingId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setBuildingId(rs.getInt("building_id"));
                expense.setCategory(rs.getInt("category"));
                expense.setDescription(rs.getString("description"));
                expense.setPeriod(rs.getString("period"));
                expense.setAmount(rs.getFloat("amount"));
                expenses.add(expense);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        return expenses;
    }

    public static ArrayList<Expense> getExpenses(int buildingId, String period) {
        Connection connection = SqliteConnection.getConnection();

        String query = "select * from expenses where building_id = ? and period like ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, buildingId);
            pstmt.setString(2, period);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Expense expense = new Expense();
                expense.setId(rs.getInt("id"));
                expense.setBuildingId(rs.getInt("building_id"));
                expense.setCategory(rs.getInt("category"));
                expense.setDescription(rs.getString("description"));
                expense.setPeriod(rs.getString("period"));
                expense.setAmount(rs.getFloat("amount"));
                expenses.add(expense);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        return expenses;
    }

    public static ArrayList<Apartment> getApartments(int buildingId) {
        Connection connection = SqliteConnection.getConnection();

        String query = "select * from apartments where building_id = ?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ArrayList<Apartment> apartments = new ArrayList<>();

        try {
            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, buildingId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Apartment apartment = new Apartment();
                apartment.setId(rs.getInt("id"));
                apartment.setBuildingId(rs.getInt("building_id"));
                apartment.setFloor(rs.getInt("floor"));
                apartment.setCode(rs.getString("code"));
                apartment.setOwnerName(rs.getString("owner_name"));
                apartment.setSurface(rs.getInt("surface"));
                apartment.setOwnerPhone(rs.getString("owner_phone"));
                apartment.setPerMille(rs.getFloat("per_mille"));
                apartment.setType(rs.getString("type"));
                apartments.add(apartment);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        return apartments;
    }
}
