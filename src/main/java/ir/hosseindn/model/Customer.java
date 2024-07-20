package ir.hosseindn.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@ToString(callSuper = true)
@SuperBuilder
@AllArgsConstructor
@Table(name = "Customer")
public class Customer extends User implements UserDetails {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    @Transient
    List<Order> orders;
    @Id
    private Long id;

}
