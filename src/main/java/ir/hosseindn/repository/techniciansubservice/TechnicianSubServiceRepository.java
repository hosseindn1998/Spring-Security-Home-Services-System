package ir.hosseindn.repository.techniciansubservice;

import ir.hosseindn.model.SubService;
import ir.hosseindn.model.TechnicianSubService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TechnicianSubServiceRepository extends JpaRepository<TechnicianSubService, Long> {
    @Query("from TechnicianSubService ts where ts.technician.id=:technicianId And ts.subService.id=:subServiceId")
    Optional<TechnicianSubService> findBySubServiceAndTechnician(@Param("technicianId") Long technicianId, @Param("subServiceId") Long subServiceId);

    @Modifying
    @Transactional
    @Query("delete from TechnicianSubService ts where ts.technician.id=:technicianId And ts.subService.id=:subServiceId")
    void deleteById(@Param("technicianId") Long technicianId, @Param("subServiceId") Long subServiceId);

    @Query("select ts.subService from TechnicianSubService ts  where ts.technician.email=:email")
    List<SubService> findByTechnician(String email);
}
