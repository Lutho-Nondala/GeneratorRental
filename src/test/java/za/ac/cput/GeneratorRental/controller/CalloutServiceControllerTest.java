package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.CalloutService;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class CalloutServiceControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CalloutServiceController CONTROLLER;

    @Autowired
    private TestRestTemplate restTemplate;

    private CalloutService calloutService;

    private String baseUrl;

    @BeforeEach
    void setUp() {
        Date doc = new Date(2023, 9, 20);
        Date doCall = new Date(2023, 9, 12);
        this.calloutService = new CalloutService.Builder()
                .date_of_collection(doc)
                .date_of_callout(doCall)
                .build();
        this.baseUrl = "http://localhost:" + this.port + "/generatorrental/calloutservice/";
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        String url = baseUrl + "create";
        ResponseEntity<CalloutService> response = this.restTemplate.postForEntity(url, this.calloutService, CalloutService.class);
        log.info(url);
        log.info("Original class: {}", response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }
}