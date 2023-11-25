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
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long supplier_id;

    private String supplier_name;

    private String supplier_contact;

    private String supplier_email;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "supplier",fetch = FetchType.LAZY)
    private Set<Product> product;

    public Supplier(Builder builder){

        this.supplier_id = builder.supplier_id;
        this.supplier_name = builder.supplier_name;
        this.supplier_contact = builder.supplier_contact;
        this.supplier_email = builder.supplier_email;
        this.address = builder.address;
        this.product = builder.product;

    }
    public static class Builder{

        private long supplier_id;

        private String supplier_name;

        private String supplier_contact;

        private String supplier_email;

        private Address address;
        private Set<Product> product;


        public Builder setSupplier_id(long supplier_id) {
            this.supplier_id = supplier_id;
            return this;
        }

        public Builder setSupplier_name(String supplier_name) {
            this.supplier_name = supplier_name;
            return this;
        }

        public Builder setSupplier_contact(String supplier_contact) {
            this.supplier_contact = supplier_contact;
            return this;
        }

        public Builder setSupplier_email(String supplier_email) {
            this.supplier_email = supplier_email;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder product(Set<Product> product){
            this.product = product;
            return this;
        }

        public Builder copy(Supplier supplier){
            this.supplier_id = supplier.supplier_id;
            this.supplier_name = supplier.supplier_name;
            this.supplier_contact = supplier.supplier_contact;
            this.supplier_email = supplier.supplier_email;
            this.address = supplier.address;
            this.product = supplier.product;
            return this;

        }

        public Supplier build(){

            return new Supplier(this);
        }

    }
}
