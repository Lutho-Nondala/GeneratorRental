package za.ac.cput.GeneratorRental.service;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.repository.AddressRepository;

import java.util.List;
import java.util.Set;

@Service
public class AddressService {
    @Autowired
    private AddressRepository REPOSITORY;

    public Address create(Address address){
        return REPOSITORY.save(address);
    }

    public Address read(long id){
        try{
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid address ID", e);
        }
    }

    public Address update(Address address){
        try{
            Address a = new Address.Builder()
                    .copy(this.read(address.getAddressId()))
                    .setStreetName(address.getStreetName())
                    .setHouseNumber(address.getHouseNumber())
                    .setPostalCode(address.getPostalCode())
                    .setTownName(address.getTownName())
                    .build();
            return REPOSITORY.save(a);
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid address ID", e);
        }
    }

    public boolean delete(long id){
        try{
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e){
            throw new IllegalArgumentException("Invalid address ID", e);
        }
    }

    public List<Address> getAll(){
        return REPOSITORY.findAll();
    }
}
