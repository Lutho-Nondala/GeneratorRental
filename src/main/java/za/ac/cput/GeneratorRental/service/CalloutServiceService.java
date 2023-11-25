package za.ac.cput.GeneratorRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.CalloutService;
import za.ac.cput.GeneratorRental.repository.CalloutServiceRepository;

import java.util.List;

@Service
public class CalloutServiceService {
    @Autowired
    private CalloutServiceRepository REPOSITORY;

    public CalloutService create(CalloutService calloutService){
        return REPOSITORY.save(calloutService);
    }

    public CalloutService read(long id) {
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Callout Service Id");
        }
    }
    public CalloutService update(CalloutService calloutService){
        try {
            CalloutService updated = new CalloutService.Builder().copy(this.read(calloutService.getService_id()))
                    .date_of_callout(calloutService.getDate_of_callout())
                    .date_of_collection(calloutService.getDate_of_collection())
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Callout Service Id");
        }
    }

    public boolean delete(long id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid Callout Service Id");
        }
    }

    public List<CalloutService> getAll(){
        return REPOSITORY.findAll();
    }
}
