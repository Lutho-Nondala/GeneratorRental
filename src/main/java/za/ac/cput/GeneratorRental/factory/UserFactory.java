package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Employee;
import za.ac.cput.GeneratorRental.domain.Role;
import za.ac.cput.GeneratorRental.domain.User;

import java.util.Set;

public class UserFactory {
    public static User createUser(String userName, String userPassword, Employee employee, Set<Role> role){
        if (Helper.isNullorEmpty(userName) || Helper.isNullorEmpty(userPassword)){
            throw new IllegalArgumentException("Username and Password required!");
        }
        if (employee == null){
            throw new IllegalArgumentException("Employee required!");
        }
        if (role == null){
            throw new IllegalArgumentException("Role is required!");
        }

        return new User.Builder()
                .userName(userName)
                .password(userPassword)
                .employee(employee)
                .role(role)
                .build();
    }
}
