package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Customer;
import za.ac.cput.GeneratorRental.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/customer/")
@Slf4j
public class CustomerController {
    @Autowired
    private CustomerService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return ResponseEntity.ok((SERVICE.create(customer)));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Customer> read(@PathVariable long id){
        return ResponseEntity.ok((SERVICE.read(id)));
    }

    @PutMapping("update")
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        return ResponseEntity.ok((SERVICE.update(customer)));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok((SERVICE.delete(id)));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Customer>> getAll(){
        return ResponseEntity.ok((SERVICE.getAll()));
    }
}
