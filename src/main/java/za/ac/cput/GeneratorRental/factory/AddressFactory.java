package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Customer;
import za.ac.cput.GeneratorRental.domain.Employee;
import za.ac.cput.GeneratorRental.domain.Supplier;

import java.util.Set;

public class AddressFactory {
    public static Address createAddresss
            (String streetName, String   houseNumber, String  postalCode, String   townName, Set<Employee> employee, Customer customer, Supplier supplier)
    {

        if (Helper.isNullorEmpty(streetName)||Helper.isNullorEmpty(houseNumber)
                || Helper.isNullorEmpty(postalCode)|| Helper.isNullorEmpty(townName)){
            throw new IllegalArgumentException("Cannot leave anything out. Everything is required for address!!");
        }
        if (employee == null){
            throw new IllegalArgumentException("Employee is required!");
        }
        if (customer == null){
            throw new IllegalArgumentException("Customer is required!");
        }
        if (supplier == null){
            throw new IllegalArgumentException("Supplier is required!");
        }
        return new Address.Builder()
                .setStreetName(streetName)
                .setHouseNumber(houseNumber)
                .setPostalCode(postalCode)
                .setTownName(townName)
                .setEmployee(employee)
                .customer(customer)
                .supplier(supplier)
                .build();
    }
}
