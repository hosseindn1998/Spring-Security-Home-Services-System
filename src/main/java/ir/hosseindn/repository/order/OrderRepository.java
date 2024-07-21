package ir.hosseindn.repository.order;

import ir.hosseindn.model.Order;
import ir.hosseindn.model.enums.OrderStatus;
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
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("from Order o where o.id=:id and (o.orderStatus='WAIT_FOR_TECHNICIAN_OFFER' or o.orderStatus='WAIT_FOR_CHOOSE_TECHNICIAN')")
    Optional<Order> isOpenToGetOffer(@Param("id") Long id);

    @Query("FROM Order o where o.subservice.name=:subServiceName AND " +
            "(o.orderStatus='WAIT_FOR_TECHNICIAN_OFFER' or o.orderStatus='WAIT_FOR_CHOOSE_TECHNICIAN')")
    List<Order> seeAllBySubService(String subServiceName);

    @Query("FROM Order o where o.customer.email=:email")
    List<Order> seeAllByCustomerEmail(String email);

    @Modifying
    @Query("update Order o set o.orderStatus=:orderStatus where o.id=:id")
    void changeOrderStatus(@Param("id") Long id, @Param("orderStatus") OrderStatus orderStatus);

    @Modifying
    @Query("update Order o set o.hasComment=true where o.id=:id")
    void orderHasComment(@Param("id") Long id);

}

