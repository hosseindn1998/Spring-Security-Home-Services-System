package ir.hosseindn.repository.order;

import ir.hosseindn.model.Offer;
import ir.hosseindn.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o where o.id=:id and (o.orderStatus=0 or o.orderStatus=1)")
    Optional<Order> isOpenToGetOffer(@Param("id") Long id);
    @Query("update from Order o set o.choosedOffer=:offer where o.id=:id")
    void chooseOffer(@Param("id")Long id,@Param("offer") Offer Offer);

}

