package za.ac.cput.GeneratorRental.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.repository.AddressRepository;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AddressRepository REPOSITORY;

    @InjectMocks
    private AddressService SERVICE;

    @Test
    void create() {
        Address address = new Address.Builder()
                .setStreetName("Loopstraat")
                .setHouseNumber("7")
                .setPostalCode("4568")
                .setTownName("NukeTown")
                .build();
        assertNotNull(SERVICE.create(address));
    }
}