package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.HQLSelect;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long sales_id;
    private double total_cost;
    private Date date;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sales", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Product> product;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "insurance_id")
    private Insurance insurance;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sales", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CalloutService> calloutService;

    public Sales(Builder builder){
        this.sales_id = builder.sales_id;
        this.total_cost = builder.total_cost;
        this.date = builder.date;
        this.customer = builder.customer;
        this.employee = builder.employee;
        this.product = builder.product;
        this.insurance = builder.insurance;
        this.calloutService = builder.calloutService;
    }
    public static class Builder{
        private long sales_id;
        private double total_cost;
        private Date date;
        private Customer customer;
        private Employee employee;
        private Set<Product> product;
        private Insurance insurance;
        private Set<CalloutService> calloutService;

        public Builder salesId(long sales_id){
            this.sales_id = sales_id;
            return this;
        }
        public Builder totalCost(double total_cost){
            this.total_cost = total_cost;
            return this;
        }
        public Builder date(Date date){
            this.date = date;
            return this;
        }
        public Builder customer(Customer customer){
            this.customer = customer;
            return this;
        }
        public Builder employee(Employee employee){
            this.employee = employee;
            return this;
        }
        public Builder product(Set<Product> product){
            this.product = product;
            return this;
        }
        public Builder insurance(Insurance insurance){
            this.insurance = insurance;
            return this;
        }
        public Builder calloutService(Set<CalloutService> calloutService){
            this.calloutService = calloutService;
            return this;
        }
        public Builder copy(Sales sales){
            this.sales_id = sales.sales_id;
            this.total_cost = sales.total_cost;
            this.date = sales.date;
            this.customer = sales.customer;
            this.employee = sales.employee;
            this.product = sales.product;
            this.insurance = sales.insurance;
            this.calloutService = sales.calloutService;
            return this;
        }
        public Sales build(){
            return new Sales(this);
        }
    }
}
