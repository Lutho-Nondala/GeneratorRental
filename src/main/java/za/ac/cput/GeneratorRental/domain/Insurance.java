package za.ac.cput.GeneratorRental.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long insurance_id;
    private String type;
    private double cost_per_day;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insurance", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Sales> sales;

    public Insurance(Builder builder){
        this.insurance_id = builder.insurance_id;
        this.type = builder.type;
        this.cost_per_day = builder.cost_per_day;
        this.sales = builder.sales;
    }

    public static class Builder{
        private long insurance_id;
        private String type;
        private double cost_per_day;
        private Set<Sales> sales;

        public Builder insuranceId(long insurance_id){
            this.insurance_id = insurance_id;
            return this;
        }

        public Builder type(String type){
            this.type = type;
            return this;
        }

        public Builder cost_per_day(double cost_per_day){
            this.cost_per_day = cost_per_day;
            return this;
        }

        public Builder sales(Set<Sales> sales){
            this.sales = sales;
            return this;
        }

        public Builder copy(Insurance insurance){
            this.insurance_id = insurance.insurance_id;
            this.type = insurance.type;
            this.cost_per_day = insurance.cost_per_day;
            this.sales = insurance.sales;
            return this;
        }

        public Insurance build(){
            return new Insurance(this);
        }
    }
}
