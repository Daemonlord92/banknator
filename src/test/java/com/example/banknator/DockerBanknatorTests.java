package com.example.banknator;

import com.example.banknator.auth.AuthService;
import com.example.banknator.auth.dto.AuthRequest;
import com.example.banknator.auth.dto.AuthResponse;
import com.example.banknator.users.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.Network;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class DockerBanknatorTests {

    @LocalServerPort
    private int port;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private AuthService authService;

    private static final Network network = Network.newNetwork();

    @Container
    private static final MySQLContainer<?> mySqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:latest"))
            .withExposedPorts(3306, 3322)
            .withDatabaseName("banknator")
            .withUsername("banknatordb")
            .withPassword("Gudmord92!")
            .withNetwork(network)
            .withNetworkAliases("mysql");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlContainer::getUsername);
        registry.add("spring.datasource.password", mySqlContainer::getPassword);
    }

    @BeforeAll
    static void beforeAll() {
        mySqlContainer.start();
    }

    @AfterAll
    static void afterAll() {
        mySqlContainer.stop();
    }

    @BeforeEach
    void setUp(ApplicationContext context) {

    }

    @Test
    void testContainerizedDataBase() {
        jdbcTemplate.execute("USE banknator");

        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM user_profile", Integer.class);
        assertEquals(3, count);
    }

    @Test
    void LoginRoute_ShouldBeSuccessful_ReturnsToken() {
        AuthRequest authRequest = new AuthRequest(
                "admin@horrorbank.com",
                "Gudmord92!"
        );
        AuthResponse authResponse = authService.login(authRequest);

        Assertions.assertNotNull(authResponse.token());
    }


}
