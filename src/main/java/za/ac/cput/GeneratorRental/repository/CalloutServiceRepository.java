package za.ac.cput.GeneratorRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.GeneratorRental.domain.CalloutService;

@Repository
public interface CalloutServiceRepository extends JpaRepository<CalloutService, Long> {
}
