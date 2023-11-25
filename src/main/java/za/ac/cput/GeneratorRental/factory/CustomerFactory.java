package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Customer;
import za.ac.cput.GeneratorRental.domain.Sales;

import java.util.Set;

public class CustomerFactory {
    public static Customer creatCustomer(String firstName, String lastName, String customer_phoneNum, String customer_email, Address address, Set<Sales> sales) {
        if (sales == null)
            return null;

        if (address == null)
            return null;

        if (Helper.isNullorEmpty(customer_phoneNum))
            return null;

        if (Helper.isNullorEmpty(customer_email))
            return null;

        if (!Helper.isValidEmail(customer_email))
            return null;

        if (Helper.isNullorEmpty(firstName))
            return null;

        if (Helper.isNullorEmpty(lastName))
            return null;

        return new Customer.Builder()
                .firstName(firstName)
                .lastNameName(lastName)
                .setCustomer_phoneNum(customer_phoneNum)
                .setCustomer_email(customer_email)
                .address(address)
                .sales(sales)
                .build();
    }
}