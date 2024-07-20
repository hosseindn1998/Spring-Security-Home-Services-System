package ir.hosseindn.dto.admin;

public record AdminSaveRequest(
        String firstName,
        String lastName,
        String nationalCode,
        String email,
        String password
) {
}
