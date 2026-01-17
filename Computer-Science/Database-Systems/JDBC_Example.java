package Database_Systems;

import java.sql.*;

/**
 * JDBC (Java Database Connectivity) Example
 * 
 * Demonstrates:
 * - Connecting to database
 * - Executing SQL queries
 * - Prepared statements
 * - Transactions
 * - ResultSet handling
 * 
 * Note: This is a template. You need to:
 * 1. Add JDBC driver to classpath
 * 2. Configure database connection
 * 3. Create database and tables
 */

public class JDBC_Example {
    
    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String USER = "username";
    private static final String PASSWORD = "password";
    
    /**
     * Create a database connection
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
    
    /**
     * Create a table
     */
    public static void createTable(Connection conn) throws SQLException {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS users (
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                email VARCHAR(100) UNIQUE NOT NULL,
                age INT
            )
            """;
        
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table created successfully");
        }
    }
    
    /**
     * Insert data using Statement (not recommended for user input)
     */
    public static void insertWithStatement(Connection conn) throws SQLException {
        String insertSQL = "INSERT INTO users (name, email, age) VALUES ('John Doe', 'john@example.com', 30)";
        
        try (Statement stmt = conn.createStatement()) {
            int rowsAffected = stmt.executeUpdate(insertSQL);
            System.out.println("Rows inserted: " + rowsAffected);
        }
    }
    
    /**
     * Insert data using PreparedStatement (recommended - prevents SQL injection)
     */
    public static void insertWithPreparedStatement(Connection conn, String name, String email, int age) 
            throws SQLException {
        String insertSQL = "INSERT INTO users (name, email, age) VALUES (?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setInt(3, age);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsAffected);
        }
    }
    
    /**
     * Query data
     */
    public static void queryData(Connection conn) throws SQLException {
        String selectSQL = "SELECT id, name, email, age FROM users";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            
            System.out.println("\nUsers:");
            System.out.println("ID\tName\t\tEmail\t\t\tAge");
            System.out.println("----------------------------------------");
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                int age = rs.getInt("age");
                
                System.out.println(id + "\t" + name + "\t\t" + email + "\t\t" + age);
            }
        }
    }
    
    /**
     * Update data
     */
    public static void updateData(Connection conn, int id, String newName) throws SQLException {
        String updateSQL = "UPDATE users SET name = ? WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            pstmt.setString(1, newName);
            pstmt.setInt(2, id);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows updated: " + rowsAffected);
        }
    }
    
    /**
     * Delete data
     */
    public static void deleteData(Connection conn, int id) throws SQLException {
        String deleteSQL = "DELETE FROM users WHERE id = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(deleteSQL)) {
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows deleted: " + rowsAffected);
        }
    }
    
    /**
     * Demonstrate transaction
     */
    public static void transactionExample(Connection conn) throws SQLException {
        try {
            // Disable auto-commit
            conn.setAutoCommit(false);
            
            // Perform multiple operations
            insertWithPreparedStatement(conn, "Alice", "alice@example.com", 25);
            insertWithPreparedStatement(conn, "Bob", "bob@example.com", 30);
            
            // Commit transaction
            conn.commit();
            System.out.println("Transaction committed successfully");
            
        } catch (SQLException e) {
            // Rollback on error
            conn.rollback();
            System.out.println("Transaction rolled back due to error: " + e.getMessage());
        } finally {
            // Re-enable auto-commit
            conn.setAutoCommit(true);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== JDBC Example ===\n");
        
        // Note: This code requires:
        // 1. MySQL JDBC driver (mysql-connector-java)
        // 2. Running MySQL database
        // 3. Proper connection configuration
        
        System.out.println("JDBC Operations:");
        System.out.println("1. Load JDBC driver");
        System.out.println("2. Establish connection");
        System.out.println("3. Create/execute statements");
        System.out.println("4. Process results");
        System.out.println("5. Close connections");
        
        System.out.println("\n=== JDBC Best Practices ===");
        System.out.println("✓ Use PreparedStatement to prevent SQL injection");
        System.out.println("✓ Use try-with-resources for automatic resource management");
        System.out.println("✓ Handle SQLException properly");
        System.out.println("✓ Use connection pooling for production");
        System.out.println("✓ Use transactions for multiple operations");
        System.out.println("✓ Close resources in finally block or use try-with-resources");
        
        System.out.println("\n=== Database Concepts ===");
        System.out.println("✓ ACID Properties:");
        System.out.println("  - Atomicity: All or nothing");
        System.out.println("  - Consistency: Valid state transitions");
        System.out.println("  - Isolation: Concurrent transactions don't interfere");
        System.out.println("  - Durability: Committed changes persist");
        
        System.out.println("\n=== SQL Operations ===");
        System.out.println("✓ CREATE: Create tables");
        System.out.println("✓ INSERT: Add data");
        System.out.println("✓ SELECT: Query data");
        System.out.println("✓ UPDATE: Modify data");
        System.out.println("✓ DELETE: Remove data");
        System.out.println("✓ JOIN: Combine tables");
        System.out.println("✓ INDEX: Improve query performance");
    }
}
