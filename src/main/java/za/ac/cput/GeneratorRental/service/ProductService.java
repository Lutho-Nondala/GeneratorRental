package za.ac.cput.GeneratorRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Product;
import za.ac.cput.GeneratorRental.domain.Supplier;
import za.ac.cput.GeneratorRental.repository.ProductRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {
    @Autowired
    private ProductRepository REPOSITORY;
    @Autowired
    private SupplierService SUPPLIER;
    @Autowired
    private AddressService ADDRESS;

    public Product create(Product product, Supplier supplier, Address address){
        try {
            Product finalProduct = REPOSITORY.save(product);

            Set<Product> products = new HashSet<>();
            products.add(finalProduct);
            supplier.setProduct(products);
            Supplier finalSupplier = SUPPLIER.create(supplier);

            address.setSupplier(finalSupplier);
            finalSupplier.setAddress(ADDRESS.create(address));

            finalProduct.setSupplier(SUPPLIER.update(finalSupplier));

            return this.update(finalProduct);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product Id");
        }
    }

    public Product read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product Id");
        }
    }

    public Product updateFrontEnd(Product product){
        try{
            Product a = this.read(product.getProduct_id());

            Product b = new Product.Builder().copy(a)
                    .setSerial_number(product.getSerial_number())
                    .setCost_per_day(product.getCost_per_day())
                    .available(product.getAvailable())
                    .build();

            return REPOSITORY.save(b);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product Id");
        }
    }

    public Product update(Product product){
        try{
            return REPOSITORY.save(product);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product Id");
        }
    }

    public boolean delete(long id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product Id");
        }
    }

    public List<Product> getAll(){
        return  REPOSITORY.findAll();
    }

    public List<Product> getByAvailable(String option){
        try {
            if (option.equals("Available")){
                return this.REPOSITORY.findByAvailable(true);
            } else {
                return this.REPOSITORY.findByAvailable(false);
            }

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid product Id");
        }
    }
}
