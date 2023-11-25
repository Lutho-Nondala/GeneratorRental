package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.Set;

@Entity
public class User {
    @NotNull
    @Id
    private String userName;
    private String userPassword;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;

    public User(){

    }

    public User(Builder builder){
        this.userName = builder.userName;
        this.userPassword = builder.password;
        this.employee = builder.employee;
        this.role = builder.role;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public static class Builder{
        private String userName, userFirstName, userLastName, password;
        private Employee employee;
        private Set<Role> role;

        public Builder userName(String userName){
            this.userName = userName;
            return this;
        }

        public Builder password(String password){
            this.password = password;
            return this;
        }

        public Builder employee(Employee employee){
            this.employee = employee;
            return this;
        }

        public Builder role(Set<Role> role){
            this.role = role;
            return this;
        }

        public Builder copy(User user){
            this.userName = user.userName;
            this.password = user.userPassword;
            this.employee = user.employee;
            this.role = user.role;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
