package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.CalloutService;
import za.ac.cput.GeneratorRental.domain.Customer;
import za.ac.cput.GeneratorRental.domain.Sales;
import za.ac.cput.GeneratorRental.service.SalesService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/sales/")
@Slf4j
public class SalesController {
    @Autowired
    private SalesService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Sales> create(
            @RequestPart("productID") long producID,
            @RequestPart("insuranceType") String insurance,
            @RequestPart("callout") CalloutService calloutService,
            @RequestPart("customer") Customer customer,
            @RequestPart("address") Address address,
            @RequestPart("employeeID") long employeeID){
        return ResponseEntity.ok(SERVICE.create(producID, insurance, calloutService, customer, address, employeeID));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Sales> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Sales> update(@RequestBody Sales sales){
        return ResponseEntity.ok(SERVICE.update(sales));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Sales>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }
}
