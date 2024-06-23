package ir.hosseindn.repository.technician;

import ir.hosseindn.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician,Long> {


}