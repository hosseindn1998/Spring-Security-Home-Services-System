package ir.hosseindn.service.user;

import ir.hosseindn.model.ConfirmationToken;
import ir.hosseindn.model.Role;
import ir.hosseindn.repository.user.UserRepository;
import ir.hosseindn.service.confirmationservice.ConfirmationTokenService;
import ir.hosseindn.service.technician.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    @Lazy
    private TechnicianService technicianService;


    public UserDetails findByEmail(String email)
            throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("user with email=%s not found", email)));
    }

    public Boolean isExistEmail(String email) {
        return userRepository.existsByEmail(email);
    }


    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getToken(token);

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("token expired");
        }

        confirmationTokenService.setConfirmedAt(token);
        enableUser(confirmationToken.getUser().getEmail());
        if (confirmationToken.getUser().getRole().equals(Role.ROLE_TECHNICIAN))
            technicianService.changeStatusToWaitForVerify(confirmationToken.getUser().getEmail());


        return "confirmed";
    }

    public void enableUser(String email) {
        userRepository.enableUser(email);
    }
}
