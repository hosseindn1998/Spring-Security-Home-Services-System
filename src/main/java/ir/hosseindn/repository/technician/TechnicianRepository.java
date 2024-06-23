package ir.hosseindn.repository.technician;

import ir.hosseindn.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician,Long> {

    Optional<Technician> findByEmailAndPassword(String email, String password);

}