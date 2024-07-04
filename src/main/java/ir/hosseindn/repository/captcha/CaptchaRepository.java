package ir.hosseindn.repository.captcha;

import ir.hosseindn.model.Captcha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaptchaRepository extends JpaRepository<Captcha,Long> {
}
