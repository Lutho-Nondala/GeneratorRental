package za.ac.cput.GeneratorRental.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.Role;
import za.ac.cput.GeneratorRental.repository.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository REPOSITORY;

    public Role create(Role role){
        return  REPOSITORY.save(role);
    }

    public Role read(String id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name");
        }
    }

    public Role update(Role role){
        try {
            Role updated = new Role.Builder().copy(this.read(role.getRoleName()))
                    .roleDescription(role.getRoleDescription())
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name");
        }
    }

    public boolean delete(String id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role name");
        }
    }

    public List<Role> getAll(){
        return REPOSITORY.findAll();
    }
}
