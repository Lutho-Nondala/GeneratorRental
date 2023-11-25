package za.ac.cput.GeneratorRental.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.GeneratorRental.domain.*;
import za.ac.cput.GeneratorRental.repository.EmployeeRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeRepository REPOSITORY;
    @Autowired
    private AddressService ADDRESS;
    @Autowired
    private UserService USER;
    @Autowired
    private JobService JOB;
    @Autowired
    private RoleService ROLE;


    public Employee create(Employee employee, Address address, User user, String title){
        try {
            Employee e = this.REPOSITORY.save(employee);

            Job job = this.JOB.findByJobTitle(title);
            e.setJob(job);

            Set<Employee> employees = new HashSet<>();
            employees.add(e);

            Address a = this.ADDRESS.create(address);
            e.setAddress(a);

            user.setEmployee(e);
            User u = this.USER.add(user, title);
            e.setUser(u);

            return this.update(e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee Id");
        }
    }

    public Employee read(long id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee Id");
        }
    }

    public Employee update(Employee employee){
        try{
            Employee updated = new Employee.Builder().copy(this.read(employee.getEmployee_id()))
                    .firstName(employee.getFirst_name())
                    .lastName(employee.getLast_name())
                    .contactNo(employee.getContact_no())
                    .build();
            return this.REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee Id");
        }
    }

    public Employee updateFrontEnd(Employee employee, String t){
        try {
            Employee e = this.read(employee.getEmployee_id());
            Job j = this.JOB.findByJobTitle(t);
            if (e.getJob().getJob_title().equals(j.getJob_title())){
                e.setFirst_name(employee.getFirst_name());
                e.setLast_name(employee.getLast_name());
                e.setContact_no(employee.getContact_no());

                return this.REPOSITORY.save(e);
            } else {
                e.setFirst_name(employee.getFirst_name());
                e.setLast_name(employee.getLast_name());
                e.setContact_no(employee.getContact_no());
                e.setJob(j);

                if (e.getJob().getJob_title().equals("Manager")){
                    Role role = this.ROLE.read("Admin");
                    Set<Role> roles = new HashSet<>();
                    roles.add(role);

                    e.getUser().setRole(roles);
                }

                return this.REPOSITORY.save(e);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Front End Update Failed");
        }
    }

    public Employee updateWhole(Employee employee){
        return this.REPOSITORY.save(employee);
    }

    public boolean delete(long id){
        try {
            Address a = this.read(id).getAddress();
            log.info(a.toString());
            this.ADDRESS.delete(a.getAddressId());
            log.info(this.ADDRESS.read(a.getAddressId()).toString());
            this.REPOSITORY.deleteById(id);
            System.out.println(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee Id");
        }
    }

    public List<Employee> getAll(){
        return REPOSITORY.findAll();
    }

    public Employee add(Employee employee){
        return this.REPOSITORY.save(employee);
    }
}
