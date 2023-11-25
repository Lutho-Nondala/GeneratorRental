package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import za.ac.cput.GeneratorRental.domain.Address;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class AddressControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private AddressController CONTROLLER;

    @Autowired
    private TestRestTemplate restTemplate;

    private Address address;
    private String baseUrl;

    @BeforeEach
    void setUp() {
        this.baseUrl = "http://localhost:" + this.port + "/generatorrental/address/";
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        this.address = new Address.Builder()
                .setStreetName("Huga Path")
                .setHouseNumber("64")
                .setPostalCode("1242")
                .setTownName("Konoha")
                .build();
        String url = baseUrl + "create";
        ResponseEntity<Address> response = this.restTemplate.postForEntity(url, this.address, Address.class);
        this.address = response.getBody();
        log.info(url);
        log.info("Original class: {}", response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void read() {
        String url = baseUrl + "read/"+ this.address.getAddressId();
        ResponseEntity<Address> response = this.restTemplate.getForEntity(url, Address.class);
        log.info("Read Updated request: {}", response);
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode())
        );
    }
}