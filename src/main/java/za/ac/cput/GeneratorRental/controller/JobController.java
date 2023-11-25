package za.ac.cput.GeneratorRental.controller;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.GeneratorRental.domain.Job;
import za.ac.cput.GeneratorRental.service.JobService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("generatorrental/job/")
@Slf4j
public class JobController {
    @Autowired
    private JobService SERVICE;

    @PostConstruct
    public void initJobs(){
        this.SERVICE.initJobs();
    }

    @PostMapping("create")
    public ResponseEntity<Job> create(@RequestBody Job job){
        return ResponseEntity.ok(SERVICE.create(job));
    }

    @GetMapping("read/{id}")
    public ResponseEntity<Job> read(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.read(id));
    }

    @PutMapping("update")
    public ResponseEntity<Job> update(@RequestBody Job job){
        return ResponseEntity.ok(SERVICE.update(job));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable long id){
        return ResponseEntity.ok(SERVICE.delete(id));
    }

    @GetMapping("getAll")
    public ResponseEntity<List<Job>> getAll(){
        return ResponseEntity.ok(SERVICE.getAll());
    }

    @GetMapping("getJobTitles")
    public ResponseEntity<List<String>> getJobTitles(){
        return ResponseEntity.ok(this.SERVICE.getJobTitles());
    }
}
