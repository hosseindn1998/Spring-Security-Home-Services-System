package ir.hosseindn.dto.user;

import ir.hosseindn.model.Role;
import ir.hosseindn.model.TechnicianStatus;
import ir.hosseindn.model.Wallet;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record UserCriteriaItems(

        Role role,
        Long id,
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        String password,
        LocalDateTime registeredDateStart,
        LocalDateTime registeredDateEnd,

        String technicianStatus,

        Double rate,
        Integer countRequests,
        Integer countDoneOrders,
        Integer totalScores,
        Integer countScores,
        Boolean isActive,
        Long walletId,
        String subServiceName

) {
}
