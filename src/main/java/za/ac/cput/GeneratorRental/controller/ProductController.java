package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Product;
import za.ac.cput.GeneratorRental.domain.Supplier;
import za.ac.cput.GeneratorRental.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/product/")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Product> create(@RequestPart("product") Product product, @RequestPart("supplier")Supplier supplier, @RequestPart("address") Address address){
        return ResponseEntity.ok(SERVICE.create(product, supplier, address));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Product> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Product> update(@RequestBody Product product){
        return ResponseEntity.ok(SERVICE.updateFrontEnd(product));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }

    @GetMapping("getByAvailable/{s}")
    public ResponseEntity<List<Product>> getByAvailable(@PathVariable String s){
        log.info(s);
        return ResponseEntity.ok(SERVICE.getByAvailable(s));
    }
}
