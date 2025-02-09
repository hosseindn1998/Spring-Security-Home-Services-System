package ir.hosseindn.repository.offer;

import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.Technician;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface OfferRepository extends JpaRepository<Offer, Long> {
    @Query("select o FROM Offer o where o.odrer.id=:orderId ORDER BY o.suggestPrice asc,o.technician.rate desc ")
    List<Offer> offerListSorted(@Param("orderId") long orderId);

    @Modifying
    @Query("update Offer o set o.isAccepted=true where o.id=:id")
    void changeOfferStatus(@Param("id") Long id);

Optional<Offer>findByOdrerAndTechnician(Order order, Technician technician);
}