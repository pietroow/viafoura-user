package com.viafoura.user.integration;

import com.viafoura.user.port.out.UserDataAccess;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

    @Autowired
    protected UserDataAccess userDataAccess;

    @LocalServerPort
    protected int port;

    @BeforeEach
    public void setupRestAssured() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        setupTestData();
    }

    @AfterEach
    public void clearDatabase() {
        cleanupTestData();
    }

    protected abstract void setupTestData();

    protected abstract void cleanupTestData();
}
