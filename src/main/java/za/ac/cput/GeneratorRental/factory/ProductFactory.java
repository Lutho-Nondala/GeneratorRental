package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Product;
import za.ac.cput.GeneratorRental.domain.Sales;
import za.ac.cput.GeneratorRental.domain.Supplier;

public class ProductFactory {
    public static Product createProduct(String serial_number, Double cost_per_day, Boolean available, Supplier supplier, Sales sales){
        if (Helper.isNullorEmpty(serial_number)){
            throw new IllegalArgumentException("Serial Number required!");
        }
        if (cost_per_day == null){
            throw new IllegalArgumentException("Cost per day required!");
        }
        if (available == null){
            throw new IllegalArgumentException("Availability required!");
        }
        if (supplier == null){
            throw new IllegalArgumentException("Supplier required!");
        }
        if (sales == null){
            throw new IllegalArgumentException("Sales required!");
        }

        return new Product.Builder()
                .setSerial_number(serial_number)
                .setCost_per_day(cost_per_day)
                .available(available)
                .setSupplier(supplier)
                .sales(sales)
                .build();
    }
}
