package za.ac.cput.GeneratorRental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Product;
import za.ac.cput.GeneratorRental.domain.Sales;
import za.ac.cput.GeneratorRental.domain.Supplier;
import za.ac.cput.GeneratorRental.repository.SupplierRepository;

import java.util.List;

@Service
@Slf4j
public class SupplierService {
    @Autowired
    private SupplierRepository REPOSITORY;
    @Autowired
    private AddressService ADDRESS;

    public Supplier create(Supplier supplier){
        return REPOSITORY.save(supplier);
    }

    public Supplier read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid supplier Id");
        }
    }

    public Supplier update(Supplier supplier){
        try {
            return REPOSITORY.save(supplier);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid supplier Id");
        }
    }

    public Supplier updateFrontEnd(Supplier supplier){
        try {
            Supplier a = this.read(supplier.getSupplier_id());
            a.setSupplier_name(supplier.getSupplier_name());
            a.setSupplier_email(supplier.getSupplier_email());
            a.setSupplier_contact(supplier.getSupplier_contact());

            return REPOSITORY.save(a);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid supplier Id");
        }
    }

    public boolean delete(long id){
        try {
            Address a = this.read(id).getAddress();
            this.ADDRESS.delete(a.getAddressId());
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid supplier Id");
        }
    }

    public List<Supplier> getAll(){
        return REPOSITORY.findAll();
    }
}
