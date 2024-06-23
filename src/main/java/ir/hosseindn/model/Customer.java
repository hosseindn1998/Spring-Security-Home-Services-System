package ir.hosseindn.model;

import jakarta.persistence.*;
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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    List<Order> orders;


}
