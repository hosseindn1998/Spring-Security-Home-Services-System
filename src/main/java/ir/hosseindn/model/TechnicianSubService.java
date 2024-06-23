package ir.hosseindn.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
    @ManyToOne(cascade = {CascadeType.MERGE})
    Technician technician;
    @ManyToOne(cascade = {CascadeType.MERGE})
    SubService subService;
}
