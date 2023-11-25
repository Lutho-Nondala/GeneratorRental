package za.ac.cput.GeneratorRental.factory;

import za.ac.cput.GeneratorRental.Util.Helper;
import za.ac.cput.GeneratorRental.domain.Employee;
import za.ac.cput.GeneratorRental.domain.Job;

import java.util.Set;

public class JobFactory {
    public static Job createJob(String job_title, double wage , Set<Employee> employee){
        if (Helper.isNullorEmpty(job_title)){
            throw new IllegalArgumentException("Job Title is required!");
        }
        if (!(wage <= 0) || !(wage >= 0)) {
            throw new IllegalArgumentException("Wage is required!");
        }
        if (employee == null){
            throw new IllegalArgumentException("Employee is required!");
        }

        return new Job.Builder()
                .setJob_title(job_title)
                .setWage(wage)
                .setEmployee(employee)
                .build();
    }
}