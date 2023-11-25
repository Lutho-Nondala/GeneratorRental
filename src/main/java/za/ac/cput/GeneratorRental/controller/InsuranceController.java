package za.ac.cput.GeneratorRental.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Insurance;
import za.ac.cput.GeneratorRental.service.InsuranceService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/insurance/")
@Slf4j
public class InsuranceController {
    @Autowired
    private InsuranceService SERVICE;

    @PostConstruct
    public void initInsurance(){
        this.SERVICE.initInsurance();
    }

    @PostMapping("create")
    public ResponseEntity<Insurance> create(@RequestBody Insurance insurance){
        return ResponseEntity.ok(SERVICE.create(insurance));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Insurance> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Insurance> update(@RequestBody Insurance insurance){
        return ResponseEntity.ok(SERVICE.update(insurance));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Insurance>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }
}
