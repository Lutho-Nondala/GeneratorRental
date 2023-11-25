package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long product_id;
    private String serial_number;
    private Double cost_per_day;
    private Boolean available;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "sales_id", referencedColumnName = "sales_id")
    private Sales sales;

    public Product (Builder builder){
        this.product_id = builder.product_id;
        this.serial_number= builder.serial_number;
        this.cost_per_day = builder.cost_per_day;
        this.available = builder.available;
        this.supplier = builder.supplier;
        this.sales = builder.sales;
    }

    public static class Builder{

        private long product_id;

        private String serial_number;

        private Double cost_per_day;

        private Boolean available;

        private Supplier supplier;
        private Sales sales;

        public Builder setProduct_id(long product_id) {
            this.product_id = product_id;
            return this;
        }

        public Builder setSerial_number(String serial_number) {
            this.serial_number = serial_number;
            return this;
        }

        public Builder setCost_per_day(Double cost_per_day) {
            this.cost_per_day = cost_per_day;
            return this;
        }

        public Builder available(boolean available){
            this.available = available;
            return this;
        }

        public Builder setSupplier(Supplier supplier) {
            this.supplier = supplier;
            return this;
        }

        public Builder sales(Sales sales){
            this.sales = sales;
            return this;
        }

        public Builder copy(Product product){

            this.product_id = product.product_id;
            this.serial_number= product.serial_number;
            this.cost_per_day = product.cost_per_day;
            this.available = product.available;
            this.supplier = product.supplier;
            this.sales = product.sales;
            return this;
        }

        public Product build(){
            return new Product(this);
        }
    }
}
