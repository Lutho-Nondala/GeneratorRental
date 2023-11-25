package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Product;
import za.ac.cput.GeneratorRental.domain.Supplier;

import java.util.Set;

public class SupplierFactory {
    public static Supplier createSupplier(String supplier_name, String supplier_contact, String supplier_email, Address address, Set<Product> product){
        if (Helper.isNullorEmpty(supplier_name) || Helper.isNullorEmpty(supplier_contact) || Helper.isNullorEmpty(supplier_email)){
            throw new IllegalArgumentException("All supplier details required!");
        }
        if (address == null){
            throw new IllegalArgumentException("Supplier address is required!");
        }
        if (product == null){
            throw new IllegalArgumentException("Product is required!");
        }

        return new Supplier.Builder()
                .setSupplier_name(supplier_name)
                .setSupplier_contact(supplier_contact)
                .setSupplier_email(supplier_email)
                .setAddress(address)
                .product(product)
                .build();
    }
}