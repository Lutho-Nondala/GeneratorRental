package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long customer_id;
    private String firstName;
    private String lastName;
    private String customer_phoneNum;
    private String customer_email;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "addressId")
    private Address address;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Sales> sales;

    private Customer(Builder builder){
        this.customer_id = builder.customer_id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.customer_phoneNum = builder.customer_phoneNum;
        this.customer_email = builder.customer_email;
        this.address = builder.address;
        this.sales = builder.sales;
    }
    public static class Builder{
        private long customer_id;
        private String firstName;
        private String lastName;
        private String customer_phoneNum;
        private String customer_email;
        private Address address;
        private Set<Sales> sales;

        public Builder setCustomer_id(long customer_id) {
            this.customer_id = customer_id;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastNameName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setCustomer_phoneNum(String customer_phoneNum) {
            this.customer_phoneNum = customer_phoneNum;
            return this;
        }

        public Builder setCustomer_email(String customer_email) {
            this.customer_email = customer_email;
            return this;
        }

        public Builder address(Address address){
            this.address = address;
            return this;
        }

        public Builder sales(Set<Sales> sales){
            this.sales = sales;
            return this;
        }

        public Builder copy(Customer customer){
            this.customer_id = customer.customer_id;
            this.firstName = customer.firstName;
            this.lastName = customer.lastName;
            this.customer_phoneNum = customer.customer_phoneNum;
            this.customer_email = customer.customer_email;
            this.address = customer.address;
            this.sales = customer.sales;
            return this;
        }

        public Customer build(){
            return new Customer(this);
        }
    }
}
