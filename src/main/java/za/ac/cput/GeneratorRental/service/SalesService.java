package za.ac.cput.GeneratorRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.*;
import za.ac.cput.GeneratorRental.repository.SalesRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SalesService {
    @Autowired
    private SalesRepository REPOSITORY;
    @Autowired
    private AddressService ADDRESS;
    @Autowired
    private CustomerService CUSTOMER;
    @Autowired
    private EmployeeService EMPLOYEE;
    @Autowired
    private  InsuranceService INSURANCE;
    @Autowired
    private CalloutServiceService CALLOUT;
    @Autowired
    private ProductService PRODUCT;

    public Sales create(long productID, String i, CalloutService callout, Customer customer, Address address, long employeeID){
        try {
            Sales sale = new Sales();

            customer.setAddress(this.ADDRESS.create(address));

            sale.setCustomer(this.CUSTOMER.create(customer));

            sale.setEmployee(this.EMPLOYEE.read(employeeID));

            sale.setInsurance(this.INSURANCE.findByType(i));

            Sales s = this.REPOSITORY.save(sale);

            callout.setSales(s);
            Set<CalloutService> c = new HashSet<>();
            c.add(this.CALLOUT.create(callout));
            s.setCalloutService(c);

            Product product = this.PRODUCT.read(productID);
            product.setSales(s);
            product.setAvailable(false);

            Set<Product> p = new HashSet<>();
            p.add(this.PRODUCT.update(product));

            s.setProduct(p);

            Date d = new Date();
            s.setDate(d);

            LocalDate out = LocalDate.of(callout.getDate_of_callout().getYear(), callout.getDate_of_callout().getMonth(), callout.getDate_of_callout().getDay());
            LocalDate collect = LocalDate.of(callout.getDate_of_collection().getYear(), callout.getDate_of_collection().getMonth(), callout.getDate_of_collection().getDay());
            Period period = Period.between(out, collect);
            int days = period.getDays();

            Double pPrice = product.getCost_per_day();

            double iPrice = this.INSURANCE.findByType(i).getCost_per_day();

            s.setTotal_cost((pPrice * days) + (iPrice * days));

            return this.update(s);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sales Id");
        }
    }

    public Sales read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid sales Id");
        }
    }

    public Sales update(Sales sales){
        try {
            return REPOSITORY.save(sales);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Sales Id");
        }
    }

    public boolean delete(long id){
        try {
            Sales s = this.read(id);
            Set<Product> p = s.getProduct();
            for (Product pp : p){
                pp.setSales(null);
                pp.setAvailable(true);
                this.PRODUCT.update(pp);
            }

            this.ADDRESS.delete(s.getCustomer().getAddress().getAddressId());

            this.CUSTOMER.delete(s.getCustomer().getCustomer_id());

            Set<CalloutService> c = s.getCalloutService();
            for (CalloutService cc : c){
                this.CALLOUT.delete(cc.getService_id());
            }

            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Sales Id");
        }
    }

    public List<Sales> getAll(){
        return REPOSITORY.findAll();
    }
}
