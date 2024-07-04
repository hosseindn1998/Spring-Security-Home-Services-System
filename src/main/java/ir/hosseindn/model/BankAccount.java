package ir.hosseindn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Table(name = "BankAccount")
public class BankAccount extends BaseEntity<Long> {
    String cardNumber;
    String cvv;
    String mm;
    String yy;
    String password;
    @Min(value = 0,message = "Your bank account balance is insufficient ")
    Long amount;
}
