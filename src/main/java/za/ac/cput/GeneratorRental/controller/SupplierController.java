package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Supplier;
import za.ac.cput.GeneratorRental.service.SupplierService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/supplier/")
@Slf4j
public class SupplierController {
    @Autowired
    private SupplierService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Supplier> create(@RequestBody Supplier supplier){
        return ResponseEntity.ok(SERVICE.create(supplier));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Supplier> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Supplier> update(@RequestBody Supplier supplier){
        return ResponseEntity.ok(SERVICE.updateFrontEnd(supplier));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Supplier>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }
}
