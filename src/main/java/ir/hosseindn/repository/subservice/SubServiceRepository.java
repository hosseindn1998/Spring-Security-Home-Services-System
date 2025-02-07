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
    @Query("update SubService s SET s.description=:description,s.basePrice=:basePrice where s.id=:id")
    void updateDescriptionAndBasePrice(@Param("id") Long id, @Param("description") String description, @Param("basePrice") Long basePrice);
}
