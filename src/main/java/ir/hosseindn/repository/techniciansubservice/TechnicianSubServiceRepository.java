package ir.hosseindn.repository.techniciansubservice;

import ir.hosseindn.model.TechnicianSubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TechnicianSubServiceRepository extends JpaRepository<TechnicianSubService, Long> {

}
