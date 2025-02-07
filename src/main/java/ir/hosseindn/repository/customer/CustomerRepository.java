package ir.hosseindn.repository.customer;

import ir.hosseindn.model.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailAndPassword(String email, String password);

    Optional<Customer> findByEmail(String email);

    @Modifying
    @Query("update Customer c set c.password=:newPassword where c.email=:email")
    void updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);
}