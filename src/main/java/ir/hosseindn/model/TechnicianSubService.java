package ir.hosseindn.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter()
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "TechnicianSubServiceManual")
public class TechnicianSubService extends BaseEntity<Long> {
    @ManyToOne(cascade = { CascadeType.MERGE}, fetch = FetchType.LAZY)
    Technician technician;
    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    SubService subService;
}
