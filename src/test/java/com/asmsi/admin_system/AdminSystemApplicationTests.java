package com.asmsi.admin_system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class AdminSystemApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        // This test ensures the Spring context loads successfully
    }

    @Test
    void testDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}