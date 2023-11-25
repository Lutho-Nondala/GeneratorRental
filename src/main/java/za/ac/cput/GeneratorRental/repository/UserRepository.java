package za.ac.cput.GeneratorRental.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.GeneratorRental.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
