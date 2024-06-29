package ir.hosseindn.repository.order;

import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import ir.hosseindn.model.OrderStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o where o.id=:id and (o.orderStatus=0 or o.orderStatus=1)")
    Optional<Order> isOpenToGetOffer(@Param("id") Long id);
    @Modifying
    @Query("update Order o set o.choosedOffer.id=:offerId where o.id=:id")
    void chooseOffer(@Param("id")Long id,@Param("offerId") Long OfferId);
    @Modifying
    @Query("update Order o set o.orderStatus=:orderStatus where o.id=:id")
    void changeOrderStatus(@Param("id") Long id,@Param("orderStatus") OrderStatus orderStatus);

}

