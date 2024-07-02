package ir.hosseindn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
@SuperBuilder
@MappedSuperclass
public class Person extends BaseEntity<Long> {
    @NotNull(message = "FirstName must not be null")
    @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
    String firstName;
    @NotNull(message = "LastName must not be null")
    @Pattern(regexp = "^[A-Z](?=.{1,29}$)[A-Za-z]*(?:\\h+[A-Z][A-Za-z]*)*$")
    String lastName;
    @Pattern(regexp = "[0-9]{10}")
    @Column(unique = true)
    String nationalCode;
    @NotNull
    @Column(unique = true)
    @Email(message = "Email must be valid")
    String email;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8}$")
    String password;
    LocalDateTime registeredDate;
    @OneToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    Wallet wallet;
}