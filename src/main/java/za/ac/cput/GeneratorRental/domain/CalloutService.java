package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@Entity
public class CalloutService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long service_id;
    private Date date_of_callout;
    private Date date_of_collection;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_id")
    private Sales sales;

    public CalloutService(Builder builder){
        this.service_id = builder.service_id;
        this.date_of_callout = builder.date_of_callout;
        this.date_of_collection = builder.date_of_collection;
        this.sales = builder.sales;
    }

    public static class Builder{
        private long service_id;
        private Date date_of_callout;
        private Date date_of_collection;
        private Sales sales;

        public Builder service_id(long service_id){
            this.service_id = service_id;
            return this;
        }

        public Builder date_of_callout(Date date_of_callout){
            this.date_of_callout = date_of_callout;
            return this;
        }

        public Builder date_of_collection(Date date_of_collection){
            this.date_of_collection = date_of_collection;
            return this;
        }

        public Builder sales(Sales sales){
            this.sales = sales;
            return this;
        }

        public Builder copy(CalloutService calloutService){
            this.service_id = calloutService.service_id;
            this.date_of_callout = calloutService.date_of_callout;
            this.sales = calloutService.sales;
            return this;
        }

        public CalloutService build(){
            return new CalloutService(this);
        }
    }
}
