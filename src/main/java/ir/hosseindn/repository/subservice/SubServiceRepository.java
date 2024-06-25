package ir.hosseindn.repository.subservice;

import ir.hosseindn.model.SubService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubServiceRepository extends JpaRepository<SubService, Long> {
    Optional<SubService> findByName(String name);

}
