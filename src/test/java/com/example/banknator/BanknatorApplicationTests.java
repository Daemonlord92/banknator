package com.example.banknator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.sql.DataSource;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@Testcontainers
class BanknatorApplicationTests {

    @LocalServerPort
    private int port;


    @Autowired
    WebTestClient webTestClient;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final Network network = Network.newNetwork();

    @Container
    private static final MySQLContainer<?> mySqlContainer = new MySQLContainer<>("mysql:latest")
            .withExposedPorts(3306)
            .withDatabaseName("banknator")
            .withUsername("banknatordb")
            .withPassword("Gudmord92!")
            .withNetwork(network)
            .withNetworkAliases("mysql")
            .withCommand("--default-authentication-plugin=mysql_native_password")
            .withCommand("--innodb_use_native_aio=0")
            .withCommand("--innodb_flush_log_at_trx_commit=2");

    static {
        mySqlContainer.start();
    }

    @Test
    void testContainerizedDataBase() {
        String dbUrl = "jdbc:mysql://localhost:" + mySqlContainer.getMappedPort(3322) + "/banknator";

        jdbcTemplate.execute("USE banknator");

        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_profiles", Integer.class);
        assertEquals(3, count);
    }

}
