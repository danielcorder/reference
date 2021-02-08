package io.mist.unfound.db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectToDB {

    // URL to connect to the database
    private static final String dbUrl = "jdbc:sqlite:C:/sqlite/";
    /**
     * Connect to a sample database
     */
    public static void connect(String fileName) {

        String connectUrl = dbUrl + fileName;
        Connection conn = null;
        try {
            // Create a connection to the database
            conn = DriverManager.getConnection(connectUrl);

            if (conn != null) {
                System.out.println("Connection to SQLite has been established.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void createNewDatabase(String fileName) {

        String fileUrl = dbUrl + fileName;
        try (Connection conn = DriverManager.getConnection(fileUrl)) {

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(String fileName) {
        // SQLite connection string
        String url = dbUrl + fileName;

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS atable (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	price real\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insert(String fileName, String name, double price) {

        String url = dbUrl + fileName;
        String sql = "INSERT INTO atable(name, price) VALUES(?,?)";

        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ConnectToDB connect = new ConnectToDB();

        String dbFile = "scratch.db";

        createNewDatabase(dbFile);
        createNewTable(dbFile);
        connect.insert(dbFile, "Last", 500);
        connect.insert(dbFile, "First", 200);
        connect.insert(dbFile, "Second", 800);
        connect.insert(dbFile, "Ninth", 700);

        connect(dbFile);

    }
}
