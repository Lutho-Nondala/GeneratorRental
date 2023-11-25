package za.ac.cput.GeneratorRental.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private long job_id;
    private String job_title;
    private double wage;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "job", orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Employee> employee;

    public Job(){}
    //private constructor
    private Job(Builder builder){
        this.job_id = builder.job_id;
        this.job_title = builder.job_title;
        this.wage = builder.wage;
        this.employee = builder.employee;
    }

    public void setJob_id(long job_id) {
        this.job_id = job_id;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public long getJob_id() {
        return job_id;
    }

    public String getJob_title() {
        return job_title;
    }

    public double getWage() {
        return wage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return job_id == job.job_id && wage == job.wage && job_title.equals(job.job_title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(job_id, job_title, wage);
    }

    @Override
    public String toString() {
        return "Job{" +
                "job_id='" + job_id + '\'' +
                ", job_title='" + job_title + '\'' +
                ", wage='" + wage + '\'' +
                '}';
    }


    //start the builder class

    public static class Builder{
        private long job_id;
        private String job_title;
        private double wage;
        private Set<Employee> employee;

        public Builder setJob_id(long job_id) {
            this.job_id = job_id;
            return this;
        }

        public Builder setEmployee(Set<Employee> employee){
            this.employee = employee;
            return this;
        }

        public Builder setJob_title(String job_title) {
            this.job_title = job_title;
            return this;
        }

        public Builder setWage(double wage) {
            this.wage = wage;
            return this;
        }


        public Builder copy(Job job){
            this.job_id = job.job_id;
            this.job_title = job.job_title;
            this.wage = job.wage;
            this.employee = job.employee;
            return this;
        }

        public Job build(){
            return new Job(this);
        }
    }
}
