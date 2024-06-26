package ir.hosseindn.repository.offer;

import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query("FROM Offer o where o.odrer=:order ORDER BY o.suggestPrice asc,o.technician.rate desc ")
    List<Offer>findAllByOdrer(@Param("order")Order order);
    @Query("update Offer o set o.isAccepted=true where o.id=:id")
    void changeOfferStatus(@Param("id") Long id);
}