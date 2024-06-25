package ir.hosseindn.repository.techniciansubservice;

import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianSubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TechnicianSubServiceRepository extends JpaRepository<TechnicianSubService, Long> {
    @Query(value = "select ts.technician from TechnicianSubService ts where ts.subService.name=:subServiceName", nativeQuery = true)
    List<Technician> findAllTechnicianBySubService(@Param("subServiceName") String subServiceName);

}
