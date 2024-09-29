package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static volatile Connection databaseConnection = null;
    private static volatile Database databaseInstance = null;

    private Database() { }

    public static Database getInstance() {
        if (databaseInstance == null) {
            synchronized (Database.class) {
                if (databaseInstance == null) {
                    databaseInstance = new Database();
                }
            }
        }
        return databaseInstance;
    }

    public static Connection connect() {
        if (databaseConnection == null || !isConnectionValid()) {
            synchronized (Database.class) {
                if (databaseConnection == null || !isConnectionValid()) {
                    try {
                        String url = "jdbc:mysql://localhost:3306/syos_system";
                        String user = "root";
                        String password = "";


                        Class.forName("com.mysql.cj.jdbc.Driver");

                        databaseConnection = DriverManager.getConnection(url, user, password);
                    } catch (SQLException | ClassNotFoundException e) {
                        System.err.println("Error establishing database connection: " + e.getMessage());
                    }
                }
            }
        }
        return databaseConnection;
    }

    public static void closeConnection() {
        if (databaseConnection != null) {
            try {
                databaseConnection.close();
                databaseConnection = null;
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    private static boolean isConnectionValid() {
        try {
            return databaseConnection != null && !databaseConnection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
