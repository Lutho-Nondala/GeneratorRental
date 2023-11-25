package za.ac.cput.GeneratorRental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Insurance;
import za.ac.cput.GeneratorRental.domain.Job;
import za.ac.cput.GeneratorRental.factory.JobFactory;
import za.ac.cput.GeneratorRental.repository.JobRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class JobService {
    @Autowired
    private JobRepository REPOSITORY;

    public Job create(Job job){
        return REPOSITORY.save(job);
    }

    public Job read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Job Id");
        }
    }

    public Job update(Job job){
        try{
            Job updated = new Job.Builder().copy(this.read(job.getJob_id()))
                    .setJob_title(job.getJob_title())
                    .setWage(job.getWage())
                    .setEmployee(job.getEmployee())
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Job Id");
        }
    }

    public boolean delete(long id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Job Id");
        }
    }

    public List<Job> getAll(){
        return REPOSITORY.findAll();
    }

    public List<String> getJobTitles(){
        List<String> titles = new ArrayList<>();
        List<Job> jobs = this.getAll();

        for (Job j: jobs){
            titles.add(j.getJob_title());
        }
        return titles;
    }

    public Job findByJobTitle(String title){
        List<Job> jobs = this.getAll();

        for (Job j: jobs){
            if (j.getJob_title().equals(title)){
                return j;
            }
        }

        return null;
    }

    public void initJobs(){
        Job a = new Job();

        Set<Job> jobs = new HashSet<>();
        jobs.add(new Job.Builder().setWage(2500.00).setJob_title("General Worker").build());
        jobs.add(new Job.Builder().setWage(3000.00).setJob_title("Supervisor").build());
        jobs.add(new Job.Builder().setWage(3500.00).setJob_title("Manager").build());
        jobs.add(new Job.Builder().setWage(4000.00).setJob_title("Owner").build());

        try {
            this.REPOSITORY.deleteAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete All Failed");
        }

        for (Job j: jobs){
            this.REPOSITORY.save(j);
        }
    }
}
