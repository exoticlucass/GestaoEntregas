package com.mycompany.gestaoentregasdao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * This class provides methods to connect to a PostgreSQL database and execute queries.
 */
public class Connection {
    private String jdbcUrl;
    private String username;
    private String password;

    public Connection() throws ClassNotFoundException {
        jdbcUrl = "jdbc:postgresql://localhost:5432/GestaoEntregas";
        username = "lucas";
        password = "123456";
        // Load PostgreSQL JDBC Driver
        Class.forName("org.postgresql.Driver");
    }
    
    /**
     * Executes a query and returns the result set.
     *
     * @param sql The SQL query to execute.
     * @return The ResultSet obtained from executing the query.
     * @throws SQLException If an SQL error occurs.
     */
    public ResultSet executeQuery(String sql) throws SQLException {
        // Establish the connection
        java.sql.Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        // Create a statement
        Statement statement = connection.createStatement();
        // Execute the query
        ResultSet resultSet = statement.executeQuery(sql);
        // Return the result set
        connection.close();
        return resultSet;      
        
    }

    /**
     * Closes the connection and statement to release resources.
     *
     * @param connection The connection to close.
     * @param statement The statement to close.
     */
    
}
