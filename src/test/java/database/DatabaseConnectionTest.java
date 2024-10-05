package database;

import junit.framework.TestCase;



import java.sql.Connection;
import java.sql.SQLException;
import junit.framework.TestCase;

public class DatabaseConnectionTest extends TestCase {


    public void testGetInstance() throws SQLException {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();


        assertSame("Singleton instance is not the same.", instance1, instance2);
    }


    public void testConnectionNotNull() throws SQLException {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection connection = instance.getConnection();


        assertNotNull("Connection should not be null", connection);
    }


    public void testConnectionValidity() throws SQLException {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection connection = instance.getConnection();


        assertTrue("Connection should be valid", connection.isValid(2));
    }


    public void testConnectionClosure() throws SQLException {
        DatabaseConnection instance = DatabaseConnection.getInstance();
        Connection connection = instance.getConnection();

        connection.close();


        assertTrue("Connection should be closed", connection.isClosed());
    }


    public void testReconnectionAfterClosure() throws SQLException {
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        Connection connection1 = instance1.getConnection();
        connection1.close();

        DatabaseConnection instance2 = DatabaseConnection.getInstance();
        Connection connection2 = instance2.getConnection();


        assertNotSame("A new connection should be established after the previous one is closed.", connection1, connection2);
        assertFalse("The new connection should be open.", connection2.isClosed());
    }
}
