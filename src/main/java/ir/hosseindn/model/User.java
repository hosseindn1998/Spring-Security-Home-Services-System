package ir.hosseindn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Long id;
    String firstName;
    String lastName;
    String nationalCode;
    @NotNull
    @Column(unique = true)
    String email;
    String password;
    @Enumerated(EnumType.STRING)
    Role role;
    LocalDateTime registeredDate;
    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Wallet wallet;
    private Boolean locked = false;
    private Boolean enabled = false;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}