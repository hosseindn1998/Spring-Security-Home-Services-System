package ir.hosseindn.dto.mainservice;

import ir.hosseindn.model.MainService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainServiceRepository extends JpaRepository <MainService,Long> {
}
