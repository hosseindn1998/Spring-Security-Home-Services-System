package ir.hosseindn.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Technician extends User implements UserDetails {
    @Enumerated(EnumType.STRING)
    TechnicianStatus technicianStatus;
    Double rate;
    Integer totalScores;
    Integer countScores;
    Boolean isActive;
    @Lob
    @ToString.Exclude
    @Lazy
    @Transient
    byte[] avatar;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    List<Comment> comments;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @ToString.Exclude
    List<Offer> offers;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    List<TechnicianSubService> technicianSubServices;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "technician")
    List<Order> orders;
    @Id
    private Long id;

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
