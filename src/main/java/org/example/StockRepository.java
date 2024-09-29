package org.example;

import database.Database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockRepository {

    private final Database database;

    public StockRepository(Database database) {
        this.database = database;
    }

    public void addStockRecord(Integer batchCode ,Date batchDate) {

        String SQL_INSERT = "INSERT INTO stock (batchCode, batchDate) VALUES (?, ?)";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, batchCode);
            pstmt.setDate(2, batchDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding store records to database: " + e.getMessage());
        }
    }

}
