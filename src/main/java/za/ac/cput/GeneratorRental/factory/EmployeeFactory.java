package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.*;

import java.util.Set;

public class EmployeeFactory {
    public static Employee build(String firstName, String lastName, String contactNo, User user, Address address, Job job, Set<Sales> sales){
        if (Helper.isNullorEmpty(firstName)){
            throw new IllegalArgumentException("First Name is required!");
        }
        if (Helper.isNullorEmpty(lastName)){
            throw new IllegalArgumentException("Last Name is required!");
        }
        if (Helper.isNullorEmpty(contactNo)){
            throw new IllegalArgumentException("Contact number is required!");
        }
        if (address == null){
            throw new IllegalArgumentException("address required!");
        }
        if (job == null){
            throw new IllegalArgumentException("Job is required!");
        }
        if (user == null){
            throw new IllegalArgumentException("user is required!");
        }
        if (sales == null){
            throw new IllegalArgumentException("sales is required!");
        }

        return new Employee.Builder().firstName(firstName).lastName(lastName).contactNo(contactNo).user(user).address(address).job(job).sales(sales).build();
    }
}
