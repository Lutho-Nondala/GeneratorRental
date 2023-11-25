package za.ac.cput.GeneratorRental.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
@NoArgsConstructor
@Entity
public class Role {
    @NotNull
    @Id
    private String roleName;
    private String roleDescription;

    public Role(Builder builder){
        this.roleName = builder.roleName;
        this.roleDescription = builder.roleDescription;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public static class Builder{
        private String roleName;
        private String roleDescription;

        public Builder roleName(String roleName){
            this.roleName = roleName;
            return this;
        }
        public Builder roleDescription(String roleDescription){
            this.roleDescription = roleDescription;
            return this;
        }

        public Builder copy(Role role){
            this.roleName = role.roleName;
            this.roleDescription = role.roleDescription;
            return this;
        }

        public Role build(){
            return new Role(this);
        }
    }
}
