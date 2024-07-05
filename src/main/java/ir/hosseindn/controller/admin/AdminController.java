package ir.hosseindn.controller.admin;

import ir.hosseindn.dto.admin.AdminLoginRequest;
import ir.hosseindn.dto.admin.AdminLoginResponse;
import ir.hosseindn.dto.captcha.SaveCaptchaRequest;
import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.dto.user.UserCriteriaItemsResponse;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.mapper.admin.AdminMapper;
import ir.hosseindn.mapper.captcha.CaptchaMapper;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.Admin;
import ir.hosseindn.model.Captcha;
import ir.hosseindn.service.admin.AdminService;
import ir.hosseindn.service.captcha.CaptchaService;
import ir.hosseindn.service.customer.CustomerService;
import ir.hosseindn.service.technician.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@Slf4j
public class AdminController {
    private final AdminService adminService;
    private final CustomerService customerService;
    private final TechnicianService technicianService;
    private final CaptchaService captchaService;

    @GetMapping("/admin-login")
    public ResponseEntity<AdminLoginResponse> adminLogin(@Valid @RequestBody AdminLoginRequest request) {
        Admin mappedAdmin = AdminMapper.INSTANCE.adminLoginRequestToModel(request);
        Admin loggedInAdmin = adminService.login(mappedAdmin.getEmail(), mappedAdmin.getPassword());
        return new ResponseEntity<>(AdminMapper.INSTANCE.modelToAdminLoginResponse(loggedInAdmin), HttpStatus.FOUND);
    }

    @GetMapping("/get-users-list")
    public ResponseEntity<UserCriteriaItemsResponse> getUserList(@Valid @RequestBody UserCriteriaItems request) {
        List<CustomerSaveResponse> customerSaveResponseList = customerService.findByCriteria(request)
                .stream()
                .map(CustomerMapper.INSTANCE::modelToUserSaveResponse)
                .toList();
        List<TechnicianSaveResponse> technicianSaveResponseList = technicianService.findByCriteria(request)
                .stream()
                .map(TechnicianMapper.INSTANCE::modelToUserSaveResponse)
                .toList();
        List<Object> userList = new ArrayList<>(customerSaveResponseList);
        userList.addAll(technicianSaveResponseList);
        UserCriteriaItemsResponse userCriteriaItemsResponse = new UserCriteriaItemsResponse(userList);
        return new ResponseEntity<>(userCriteriaItemsResponse, HttpStatus.FOUND);
    }
    @GetMapping("/add-captcha")
    public String getAddCaptchaPage(){
        return "add_captcha";
    }
    @GetMapping("/captcha")
    public ResponseEntity<String> addCaptcha(@Valid @RequestParam String answer,
                             @Valid @RequestParam String filePath) {
        try {
            captchaService.addNewCaptcha(answer, filePath);
        }catch (IOException e){
            log.warn(e.getMessage());
        }
        return new ResponseEntity<>("added successfully",HttpStatus.CREATED);
    }
    @GetMapping("/upload")
    public String getImageUploadPage(){
        return "image_upload";
    }
    @PostMapping("/image-upload")
    public String imageUpload(@Valid @RequestBody SaveCaptchaRequest request) {
        Captcha captcha = CaptchaMapper.INSTANCE.saveCaptchaRequestToModel(request);

        return "image_upload";
    }

}
