package com.thoughtworks.securityinourdna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String databaseDescriptor = "jdbc:derby:memory:" + "injection" + ";create=true;territory=en_US";
    private final String usersTableSql = "create table invoices (text varchar(255))";

    public Connection createInMemoryDatabase() throws SQLException {
        dropDatabaseIfItAlreadyExists();

        final Connection conn = DriverManager.getConnection(databaseDescriptor);
        conn.createStatement().execute(usersTableSql);

        return conn;
    }

    private void dropDatabaseIfItAlreadyExists() {
        try {
            DriverManager.getConnection("jdbc:derby:memory:" + "injection" + ";drop=true");
        } catch (SQLException e) {
            // Database doesn't exist in memory yet, no worries
        }
    }
}
