package za.ac.cput.GeneratorRental.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.User;
import za.ac.cput.GeneratorRental.service.UserService;

import java.util.List;

@RestController
@RequestMapping("generatorrental/user/")
@Slf4j
public class UserController {
    @Autowired
    private UserService SERVICE;

    @PostConstruct
    public void initRolesAndOwner(){
        this.SERVICE.initRolesAndOwner();
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> create(@RequestBody User user){
        return ResponseEntity.ok(SERVICE.create(user));
    }

    @GetMapping("read/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> read(@PathVariable String id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<User> update(@RequestBody User user){
        return ResponseEntity.ok(SERVICE.updateFrontEnd(user));
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<Boolean> delete(@PathVariable String id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    @PreAuthorize("hasRole('Admin')")
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }
}
