package ir.hosseindn.service.admin;

import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.model.Admin;
import ir.hosseindn.repository.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;

    public Admin login(String email, String Password) {
        return adminRepository.findByEmailAndPassword(email, Password).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }
}
