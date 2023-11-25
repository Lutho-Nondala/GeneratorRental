package za.ac.cput.GeneratorRental.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.CalloutService;
import za.ac.cput.GeneratorRental.service.CalloutServiceService;

import java.util.List;


@RestController
@RequestMapping("generatorrental/calloutservice/")
@Slf4j
public class CalloutServiceController {
    @Autowired
    private CalloutServiceService SERVICE;

    @PostMapping("create")
    public ResponseEntity<CalloutService> create(@RequestBody CalloutService calloutService){
        return ResponseEntity.ok(SERVICE.create(calloutService));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<CalloutService> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<CalloutService> update(@RequestBody CalloutService calloutService){
        return ResponseEntity.ok(SERVICE.update(calloutService));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<CalloutService>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }
}
