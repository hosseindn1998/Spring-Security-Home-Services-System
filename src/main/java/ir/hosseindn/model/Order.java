package ir.hosseindn.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
    String description;
    @NotNull
    LocalDateTime dateForDo;
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