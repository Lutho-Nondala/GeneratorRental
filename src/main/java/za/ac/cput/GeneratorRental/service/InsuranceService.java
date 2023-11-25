package za.ac.cput.GeneratorRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Insurance;
import za.ac.cput.GeneratorRental.repository.InsuranceRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class InsuranceService {
    @Autowired
    private InsuranceRepository REPOSITORY;

    public Insurance create(Insurance insurance){
        return REPOSITORY.save(insurance);
    }

    public Insurance read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid insurance Id");
        }
    }

    public Insurance update(Insurance insurance){
        try{
            Insurance updated = new Insurance.Builder().copy(this.read(insurance.getInsurance_id()))
                    .type(insurance.getType())
                    .cost_per_day(insurance.getCost_per_day())
                    .sales(insurance.getSales())
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid insurance Id");
        }
    }

    public boolean delete(long id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid insurance Id");
        }
    }

    public List<Insurance> getAll(){
        return REPOSITORY.findAll();
    }

    public Insurance findByType(String t){
        List<Insurance> types = this.getAll();

        for (Insurance i : types){
            if (t.equals(i.getType())){
                return i;
            }
        }
        return null;
    }
    public void initInsurance(){
        Set<Insurance> types = new HashSet<>();

        types.add(new Insurance.Builder().cost_per_day(120).type("BitSurance").build());
        types.add(new Insurance.Builder().cost_per_day(122).type("Securance").build());
        types.add(new Insurance.Builder().cost_per_day(118).type("SafeSpace").build());

        try {
            this.REPOSITORY.deleteAll();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Delete All Failed");
        }

        for (Insurance i: types){
            this.REPOSITORY.save(i);
        }
    }
}
