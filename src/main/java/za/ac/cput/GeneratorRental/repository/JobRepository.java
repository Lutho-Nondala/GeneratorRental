package za.ac.cput.GeneratorRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.GeneratorRental.domain.Job;

import java.util.Set;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
