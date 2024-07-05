package ir.hosseindn.mapper.captcha;

import ir.hosseindn.dto.captcha.SaveCaptchaRequest;
import ir.hosseindn.dto.captcha.SaveCaptchaResponse;
import ir.hosseindn.model.Captcha;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CaptchaMapper {
    CaptchaMapper INSTANCE = Mappers.getMapper(CaptchaMapper.class);

    Captcha saveCaptchaRequestToModel(SaveCaptchaRequest request);

    SaveCaptchaResponse modelToSaveCaptchaResponse(Captcha captcha);
}
