package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.domain.*;

import java.util.Date;
import java.util.Set;

public class SalesFactory {
    public static Sales createSales(Double total_cost, Date date, Customer customer, Employee employee, Set<Product> product, Insurance insurance, Set<CalloutService> calloutService){
        if (total_cost == null){
            throw new IllegalArgumentException("Total Cost required!");
        }
        if (date == null){
            throw new IllegalArgumentException("Date required!");
        }
        if (customer == null){
            throw new IllegalArgumentException("Customer required!");
        }
        if (employee == null){
            throw new IllegalArgumentException("Employee required!");
        }
        if (product == null){
            throw new IllegalArgumentException("Product required!");
        }
        if (insurance == null){
            throw new IllegalArgumentException("Insurance required!");
        }
        if (calloutService == null){
            throw new IllegalArgumentException("Call-out service required!");
        }

        return new Sales.Builder()
                .totalCost(total_cost)
                .date(date)
                .customer(customer)
                .employee(employee)
                .product(product)
                .insurance(insurance)
                .calloutService(calloutService)
                .build();
    }
}
