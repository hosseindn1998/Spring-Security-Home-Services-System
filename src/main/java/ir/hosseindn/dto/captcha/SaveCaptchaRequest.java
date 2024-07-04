package ir.hosseindn.dto.captcha;

import jakarta.validation.constraints.Pattern;

public record SaveCaptchaRequest(
        @Pattern(regexp = "^[^\\s]+:\\\\+[^\\s]+\\.(jpg|jpeg)$",message = "Captcha image not found or not valid file path")
        String pathFile,
        String answer
) {
}
