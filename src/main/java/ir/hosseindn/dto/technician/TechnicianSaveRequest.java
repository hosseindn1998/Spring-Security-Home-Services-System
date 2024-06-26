package ir.hosseindn.dto.technician;

import ir.hosseindn.dto.wallet.WalletSaveRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.ToString;
import org.springframework.context.annotation.Lazy;

public record TechnicianSaveRequest(
        TechnicianSaveRequestWithoutPath technician,
        String imagePath
) {
}
