package ir.hosseindn.model;

import jakarta.persistence.*;
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
@Table(name = "offer")
public class Offer extends BaseEntity<Long> {
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    Order odrer;
    LocalDateTime dateOfOfferToStart;
    Long suggestPrice;
    LocalDateTime dateOfOfferToDone;
    @ManyToOne(fetch = FetchType.LAZY)
    Technician technician;
    Boolean isAccepted;

}
