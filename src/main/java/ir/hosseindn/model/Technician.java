package ir.hosseindn.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter()
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "Technician")
public class Technician extends Person {
    @Enumerated(EnumType.ORDINAL)
    TechnicianStatus technicianStatus;
    @NotNull
    @Min(0)
    @Max(5)
    double rate;
    @Min(0)
    Long TotalScores;
    Long countScores;
    boolean isActive;
    @Lob
    @ToString.Exclude
    @Lazy
    byte[] avatar;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    List<Offer> offers;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician", cascade = {CascadeType.REMOVE})
    @ToString.Exclude
    List<TechnicianSubService> technicianSubServices;


}
