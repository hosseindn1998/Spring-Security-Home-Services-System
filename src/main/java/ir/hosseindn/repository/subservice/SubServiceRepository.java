package ir.hosseindn.repository.subservice;

import ir.hosseindn.model.SubService;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    Optional<SubService> findByName(String name);
    @Modifying
    @Transactional
    @Query("update SubService s set s.basePrice=:newBasePrice ,s.description=:description where s.name=:name")
    void updateByName(@Param("name") String name, @Param("newBasePrice") Long newBasePrice,@Param("description") String description);
}
