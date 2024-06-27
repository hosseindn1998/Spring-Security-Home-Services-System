package ir.hosseindn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "subService")
public class SubService extends BaseEntity<Long> {
    @Column(unique = true)
    String name;
    @Min(0)
    Long basePrice;
    @NotNull
    String description;
    @ManyToOne(fetch = FetchType.LAZY)
    MainService mainService;
    @ToString.Exclude
    @OneToMany(mappedBy = "subservice", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    List<Order> orders;
    @OneToMany(mappedBy = "subService",cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    List<TechnicianSubService>technicianSubServices;
}
