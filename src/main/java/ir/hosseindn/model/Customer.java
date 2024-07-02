package ir.hosseindn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Table(name = "customer")
public class Customer extends Person {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @Transient
    List<Order> orders;
    @Enumerated
    @NotNull
    @Min(0)
    @Max(2)
    Roles role;


}
