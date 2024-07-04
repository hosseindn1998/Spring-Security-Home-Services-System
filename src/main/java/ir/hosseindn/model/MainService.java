package ir.hosseindn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
@Table(name = "service")
public class MainService extends BaseEntity<Long> {
    @Column(unique = true)
    String name;
    @OneToMany(mappedBy = "mainService", cascade = {CascadeType.REMOVE},fetch = FetchType.LAZY)
    @ToString.Exclude
    List<SubService> subServices;

}
