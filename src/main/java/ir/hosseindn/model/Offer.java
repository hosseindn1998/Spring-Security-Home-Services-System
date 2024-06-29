package ir.hosseindn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
    @ManyToOne
    @ToString.Exclude
    Order odrer;
    LocalDate dateOfOfferToStart;
    @Min(0)
    @NotNull
    Long suggestPrice;
    LocalDate dateOfOfferToDone;
    @ManyToOne
    Technician technician;
    Boolean isAccepted;

}
