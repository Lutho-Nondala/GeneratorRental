package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long employee_id;
    private String first_name;
    private String last_name;
    private String contact_no;
    @OneToOne(mappedBy = "employee")
    private User user;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", referencedColumnName = "job_id")
    private Job job;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Sales> sales;

    public Employee(){}

    public Employee(Builder builder){
        this.employee_id = builder.employeeId;
        this.first_name = builder.firstName;
        this.last_name = builder.lastName;
        this.contact_no = builder.contactNo;
        this.user = builder.user;
        this.job = builder.job;
        this.address = builder.address;
        this.sales = builder.sales;
    }

    public Set<Sales> getSales() {
        return sales;
    }

    public void setSales(Set<Sales> sales) {
        this.sales = sales;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public static class Builder{
        private long employeeId;
        private String firstName, lastName, contactNo;
        private User user;
        private Job job;
        private Address address;
        private Set<Sales> sales;

        public Builder employeeId(long employeeId){
            this.employeeId = employeeId;
            return this;
        }

        public Builder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public Builder contactNo(String contactNo){
            this.contactNo = contactNo;
            return this;
        }

        public Builder user(User user){
            this.user = user;
            return this;
        }

        public Builder job(Job job){
            this.job = job;
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

        public Builder copy(Employee employee){
            this.employeeId = employee.employee_id;
            this.firstName = employee.first_name;
            this.lastName = employee.last_name;
            this.contactNo = employee.contact_no;
            this.user = employee.user;
            this.job = employee.job;
            this.address = employee.address;
            this.sales = employee.sales;
            return this;
        }

        public Employee build(){
            return new Employee(this);
        }
    }
}
