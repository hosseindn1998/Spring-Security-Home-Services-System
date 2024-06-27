package ir.hosseindn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
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
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    SubService subservice;
    @ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    Customer customer;
    @Min(0L)
    Long suggestedPrice;
    @NotNull
    Boolean isPaid;
    @NotNull
    @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
    String description;
    @NotNull
    LocalDate dateForDo;
    @NotNull
    @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
    String address;
    @Enumerated
    OrderStatus orderStatus;
    @OneToMany(mappedBy = "odrer", cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY)
    @ToString.Exclude
    List<Offer> offers;
    @OneToOne
    Offer choosedOffer;
    @OneToOne
    Comment comment;
}