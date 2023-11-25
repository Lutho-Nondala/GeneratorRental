package za.ac.cput.GeneratorRental.service;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import za.ac.cput.GeneratorRental.domain.*;
import za.ac.cput.GeneratorRental.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository REPOSITORY;
    @Autowired
    private EmployeeService EMPLOYEE;
    @Autowired
    private AddressService ADDRESS;
    @Autowired
    private JobService JOB;
    @Autowired
    private RoleService ROLE;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EntityManager entityManager;

    public User create(User user){
        try {
            return REPOSITORY.save(user);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid user credential");
        }
    }

    public User read(String id){
        try {
            return REPOSITORY.findById(id).get();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee username");
        }
    }

    public User update(User user){
        try {
            User updated = new User.Builder().copy(this.read(user.getUserName()))
                    .password(encodePassword(user.getUserPassword()))
                    .employee(user.getEmployee())
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee username");
        }
    }

    public User updateFrontEnd(User user){
        try {
            User updated = new User.Builder().copy(this.read(user.getUserName()))
                    .password(encodePassword(user.getUserPassword()))
                    .build();
            return REPOSITORY.save(updated);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee username");
        }
    }

    public boolean delete(String id){
        try {
            REPOSITORY.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee username");
        }
    }

    public List<User> getAll(){
        return StreamSupport.stream(this.REPOSITORY.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User add(User user, String title){
        try {
            if (title.equals("Manager")){
                Role role = this.ROLE.read("Admin");
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                user.setRole(roles);
                user.setUserPassword(encodePassword(user.getUserPassword()));
                return this.REPOSITORY.save(user);
            } else {
                Role role = this.ROLE.read("User");
                Set<Role> roles = new HashSet<>();
                roles.add(role);
                user.setRole(roles);
                user.setUserPassword(encodePassword(user.getUserPassword()));
                return this.REPOSITORY.save(user);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid employee user credentials");
        }
    }
    @Transactional
    public void initRolesAndOwner(){
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        this.ROLE.create(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for new users");
        this.ROLE.create(userRole);

        User adminUser = new User();
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        adminUser.setUserName("Cliford");
        adminUser.setUserPassword(encodePassword("SpaceHolePuncher"));
        User upUser = REPOSITORY.save(adminUser);

        Job job = this.JOB.findByJobTitle("Owner");

        Employee employee = new Employee();
        employee.setFirst_name("Akainu");
        employee.setLast_name("Sakazuki");
        employee.setContact_no("0785689568");
        employee.setUser(upUser);
        employee.setJob(job);
        Employee upEmp = this.EMPLOYEE.add(employee);

        Address address = new Address();
        address.setStreetName("Magma Lane");
        address.setHouseNumber("1");
        address.setPostalCode("3142");
        address.setTownName("Marineford");
        Set<Employee> emps = new HashSet<>();
        emps.add(upEmp);
        address.setEmployee(emps);
        upEmp.setAddress(this.ADDRESS.create(address));

        upUser.setEmployee(this.EMPLOYEE.updateWhole(upEmp));
        this.REPOSITORY.save(upUser);
        //log.info(this.update(upUser).getUserName());
    }

    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }

}