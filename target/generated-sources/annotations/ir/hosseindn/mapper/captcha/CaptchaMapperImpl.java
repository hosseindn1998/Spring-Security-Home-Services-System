package ir.hosseindn.mapper.captcha;

import ir.hosseindn.dto.captcha.SaveCaptchaRequest;
import ir.hosseindn.dto.captcha.SaveCaptchaResponse;
import ir.hosseindn.model.Captcha;
import java.util.Arrays;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-05T00:35:07+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class CaptchaMapperImpl implements CaptchaMapper {

    @Override
    public Captcha saveCaptchaRequestToModel(SaveCaptchaRequest request) {
        if ( request == null ) {
            return null;
        }

        Captcha.CaptchaBuilder<?, ?> captcha = Captcha.builder();

        captcha.pathFile( request.pathFile() );
        captcha.answer( request.answer() );

        return captcha.build();
    }

    @Override
    public SaveCaptchaResponse modelToSaveCaptchaResponse(Captcha captcha) {
        if ( captcha == null ) {
            return null;
        }

        Long id = null;
        String answer = null;
        byte[] picture = null;

        id = captcha.getId();
        answer = captcha.getAnswer();
        byte[] picture1 = captcha.getPicture();
        if ( picture1 != null ) {
            picture = Arrays.copyOf( picture1, picture1.length );
        }

        SaveCaptchaResponse saveCaptchaResponse = new SaveCaptchaResponse( id, answer, picture );

        return saveCaptchaResponse;
    }
}
