package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Role;
import za.ac.cput.GeneratorRental.service.RoleService;

import java.util.List;
@RestController
@RequestMapping("generatorrental/role/")
@Slf4j

public class RoleController {
    @Autowired
    private RoleService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Role> create(@RequestBody Role role){
        return ResponseEntity.ok(SERVICE.create(role));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Role> read(@PathVariable String id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Role> update(@RequestBody Role role){
        return ResponseEntity.ok(SERVICE.update(role));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }
}
