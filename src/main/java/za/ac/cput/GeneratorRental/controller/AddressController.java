package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.service.AddressService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/address/")
@Slf4j
public class AddressController {
    @Autowired
    private AddressService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Address> create(@RequestBody Address address){
        return ResponseEntity.ok(this.SERVICE.create(address));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Address> read(@PathVariable long id){
        return ResponseEntity.ok(this.SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Address> update(@RequestBody Address address){
        return ResponseEntity.ok(this.SERVICE.update(address));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(this.SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Address>> getAll(){
        return ResponseEntity.ok(this.SERVICE.getAll());
    }
}
