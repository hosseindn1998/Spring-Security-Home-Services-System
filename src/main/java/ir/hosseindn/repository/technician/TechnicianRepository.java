package ir.hosseindn.repository.technician;

import ir.hosseindn.model.Technician;
import ir.hosseindn.model.TechnicianStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    Optional<Technician> findByEmailAndPassword(String email, String password);

    Optional<Technician> findByEmailOrNationalCode(String email, String nationalCode);

    Optional<Technician> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("update Technician t set t.password=:password where t.email=:email")
    void updatePassword(@Param("email") String email, @Param("password") String password);
    @Query("UPDATE Technician t set t.technicianStatus=:technicianStatus where t.email=:email")
    void updateTechnicianStatus(@Param("technicianStatus") TechnicianStatus technicianStatus,@Param("email") String email);
}