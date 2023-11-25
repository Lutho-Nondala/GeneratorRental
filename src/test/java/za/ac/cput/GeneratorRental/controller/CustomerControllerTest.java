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
import za.ac.cput.GeneratorRental.domain.Customer;
import za.ac.cput.GeneratorRental.domain.Sales;
import za.ac.cput.GeneratorRental.factory.CustomerFactory;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Slf4j
class CustomerControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private CustomerController CONTROLLER;

    @Autowired
    private TestRestTemplate restTemplate;

    private Address address;
    private Customer customer;
    private Customer cus;
    private Set<Sales> sales = new HashSet<>();
    private String baseUrl;

    @BeforeEach
    void setUp() {
        address = new Address.Builder()
                .setTownName("Smallville")
                .setPostalCode("5658")
                .setHouseNumber("8")
                .setStreetName("Liefdestraat")
                .build();
        this.customer = new Customer.Builder()
                .firstName("Jane")
                .lastNameName("Doe")
                .setCustomer_phoneNum("0865985658")
                .setCustomer_email("bigPumPum@gmail.com")
                .address(address)
                .build();
        //Customer cus = CustomerFactory.creatCustomer("Jane", "Doe", "0785623569", "janeGirl@gamail.com", address, sales);
        this.baseUrl = "http://localhost:" + this.port + "/generatorrental/customer/";
    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void create() {
        String url = baseUrl + "create";
        ResponseEntity<Customer> response = this.restTemplate.postForEntity(url, this.customer, Customer.class);
        log.info(url);
        log.info("Original class: {}", response.getBody());
        assertAll(
                () -> assertEquals(HttpStatus.OK, response.getStatusCode()),
                () ->  assertNotNull(response.getBody())
        );
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void update() {
        String url = baseUrl + "update";
        cus = new Customer.Builder()
                .setCustomer_id(252)
                .firstName("Jane")
                .lastNameName("Doe")
                .setCustomer_phoneNum("0865985555")
                .setCustomer_email("SpicedBeefStew@gmail.com")
                .build();
        this.restTemplate.put(url, this.cus);
        log.info(url);
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void delete() {
        String url = baseUrl + "delete/"+2;
        this.restTemplate.delete(url);
    }
}