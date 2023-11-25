package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Role;

public class RoleFactory {
    public static Role createRole(String roleName, String roleDescription){
        if (Helper.isNullorEmpty(roleName) || Helper.isNullorEmpty(roleDescription)){
            throw new IllegalArgumentException("Role name and Role Description required!");
        }

        return new Role.Builder()
                .roleName(roleName)
                .roleDescription(roleDescription)
                .build();
    }
}
