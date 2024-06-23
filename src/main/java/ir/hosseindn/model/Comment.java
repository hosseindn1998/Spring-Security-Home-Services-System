package ir.hosseindn.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "comment")

public class Comment extends BaseEntity<Long> {
    @NotNull
    @Min(1)
    @Max(5)
    Integer rate;
    @NotNull
    String title;
    @NotNull
    String description;
    @OneToOne
    Order order;
    @ManyToOne
    Technician technician;
}
