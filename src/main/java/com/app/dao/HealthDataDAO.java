package com.app.dao;

import java.sql.*;

import com.app.model.HealthData;
import com.app.util.DatabaseConnection;

public class HealthDataDAO {
    private static Connection connection;
    private static DatabaseConnection DBConnectionInstance;

    static {
        try {
            DBConnectionInstance = DatabaseConnection.getInstance();
            connection = DBConnectionInstance.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addRecords(HealthData record) {

        String year = record.getYear();
        String brandName = record.getBrandName();
        String genericName = record.getGenericName();
        String coverageType = record.getCoverageType();
        Double totalSpending = Double.parseDouble(record.getTotalSpending());
        int serialId = Integer.parseInt(record.getserialId());
        ResultSet rs;




        try {
            Statement statement = connection.createStatement();
            PreparedStatement stmt=connection.prepareStatement("Insert into healthdb values(default,?,?,?,?,?,?)");

            stmt.setInt(1,serialId);
            stmt.setString(2,year);
            stmt.setString(3,brandName);
            stmt.setString(4,genericName);
            stmt.setString(5,coverageType);
            stmt.setDouble(6,totalSpending);

            String sql = "SELECT serial_id from healthdb WHERE serial_id ="+serialId;

            rs = statement.executeQuery(sql);

            if(rs.next() == false) {
                int result = stmt.executeUpdate();
            } else {
                System.out.println("Ignoring duplicate record, serial_id ="+serialId+"\n");
            }


        }catch(SQLException se) {
            se.printStackTrace();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}