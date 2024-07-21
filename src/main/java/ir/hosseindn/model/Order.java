package ir.hosseindn.model;

import ir.hosseindn.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "odrer")
public class Order extends BaseEntity<Long> {
    String mainServiceName;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    SubService subservice;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    Customer customer;
    Long suggestedPrice;
    String description;
    LocalDateTime creationDate;
    LocalDateTime dateForDo;
    String address;
    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;
    @OneToMany(mappedBy = "odrer", cascade = {CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Offer> offers;
    @OneToOne(fetch = FetchType.LAZY)
    Offer choosedOffer;
    @ManyToOne(fetch = FetchType.LAZY)
    Technician technician;
    Boolean hasComment;
}