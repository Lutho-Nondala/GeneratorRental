package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Address;
import za.ac.cput.GeneratorRental.domain.Employee;
import za.ac.cput.GeneratorRental.domain.User;
import za.ac.cput.GeneratorRental.service.EmployeeService;
import za.ac.cput.GeneratorRental.service.JobService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("generatorrental/employee/")
@Slf4j
public class EmployeeController {
    @Autowired
    private EmployeeService SERVICE;

    @PostMapping("create")
    public ResponseEntity<Employee> create(
            @RequestPart("employee") Employee employee,
            @RequestPart("address") Address address,
            @RequestPart("user") User user,
            @RequestPart("title") String title){
        return ResponseEntity.ok(SERVICE.create(employee, address, user, title));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Employee> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Employee> update(
            @RequestPart("employee") Employee employee,
            @RequestPart("title") String e){
        return ResponseEntity.ok(SERVICE.updateFrontEnd(employee,e));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Employee>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }

}
