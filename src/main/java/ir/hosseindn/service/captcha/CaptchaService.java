package ir.hosseindn.service.captcha;

import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.Captcha;
import ir.hosseindn.repository.captcha.CaptchaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class CaptchaService {
private final CaptchaRepository captchaRepository;
    public void addNewCaptcha(String answer, String filePath) throws IOException {
        Captcha captcha = Captcha.builder()
                .answer(answer)
                .pathFile(filePath)
                .picture(Files.readAllBytes(Paths.get(filePath)))
                .build();
        captchaRepository.save(captcha);
    }
    public Captcha findById(Long id){
        return captchaRepository.findById(id).orElseThrow(
                ()->new NotFoundException(String.format("captcha with id = %s not found.",id))
        );
    }
    public Captcha save(Captcha captcha){
        return captchaRepository.save(captcha);
    }
}
