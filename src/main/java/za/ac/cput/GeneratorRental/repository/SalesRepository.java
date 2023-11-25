package za.ac.cput.GeneratorRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.GeneratorRental.domain.Sales;

@Repository
public interface SalesRepository extends JpaRepository<Sales, Long> {
}
