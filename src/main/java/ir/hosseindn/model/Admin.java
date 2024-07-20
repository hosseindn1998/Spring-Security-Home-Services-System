package ir.hosseindn.model;

import ir.hosseindn.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@SuperBuilder
@ToString(callSuper = true)
@Table(name = "admins")
public class Admin extends User implements UserDetails {
    @Enumerated(EnumType.STRING)
    Role role;
    @Id
    private Long id;


    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonLocked() {
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
