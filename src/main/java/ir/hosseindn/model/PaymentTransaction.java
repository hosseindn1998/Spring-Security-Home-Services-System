package ir.hosseindn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "PaymentTransaction")
public class PaymentTransaction extends BaseEntity<Long> {
    Long orderId;
    Long suggestPrice;
    String cardNumber;
    String cvv;
    String mm;
    String yy;
    String password;
    LocalDateTime time;
}
