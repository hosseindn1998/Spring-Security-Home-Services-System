package ir.hosseindn.repository.mainservice;

import ir.hosseindn.model.MainService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainServiceRepository extends JpaRepository<MainService, Long> {


}
