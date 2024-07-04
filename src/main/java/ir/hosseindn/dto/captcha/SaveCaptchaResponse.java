package ir.hosseindn.dto.captcha;

public record SaveCaptchaResponse(
        Long id,
        String answer,
        byte[] picture

) {
}
