package za.ac.cput.GeneratorRental.domain;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long addressId;
    private String streetName;
    private String houseNumber;
    private String postalCode;
    private String townName;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "address", fetch = FetchType.LAZY)
    private Set<Employee> employee;
    @OneToOne(mappedBy = "address")
    private Customer customer;
    @OneToOne(mappedBy = "address")
    private Supplier supplier;

    public Address() {
    }

    public Address(Builder builder) {
        this.addressId = builder.addressId;
        this.streetName = builder.streetName;
        this.houseNumber = builder.houseNumber;
        this.postalCode = builder.postalCode;
        this.townName = builder.townName;
        this.employee = builder.employee;
        this.customer = builder.customer;
        this.supplier = builder.supplier;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public long getAddressId() {
        return addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getTownName() {
        return townName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId=" + addressId +
                ", streetName='" + streetName + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", townName='" + townName + '\'' +
                '}';
    }

    public static class Builder{
        private long addressId;
        private String streetName;
        private String houseNumber;
        private String postalCode;
        private String townName;
        private Set<Employee> employee;
        private Customer customer;
        private Supplier supplier;

        public Builder setAddressId(long addressId) {
            this.addressId = addressId;
            return this;
        }

        public Builder setEmployee(Set<Employee> employee){
            this.employee = employee;
            return this;
        }

        public Builder setStreetName(String streetName) {
            this.streetName = streetName;

            return this;
        }

        public Builder setHouseNumber(String houseNumber) {
            this.houseNumber = houseNumber;

            return this;
        }

        public Builder setPostalCode(String postalCode) {
            this.postalCode = postalCode;

            return this;
        }

        public Builder setTownName(String townName) {
            this.townName = townName;

            return this;
        }

        public Builder customer(Customer customer){
            this.customer = customer;
            return this;
        }

        public Builder supplier(Supplier supplier){
            this.supplier = supplier;
            return this;
        }

        public Builder copy(Address address){
            this.addressId = address.addressId;
            this.streetName = address.streetName;
            this.houseNumber = address.houseNumber;
            this.postalCode = address.postalCode;
            this.townName = address.townName;
            this.employee = address.employee;
            this.customer = address.customer;
            this.supplier = address.supplier;
            return this;

        }

        public Address build(){

            return new Address(this);
        }

    } // builder
}
