package ir.hosseindn.service.admin;

import ir.hosseindn.dto.customer.CustomerSaveResponse;
import ir.hosseindn.dto.technician.TechnicianSaveResponse;
import ir.hosseindn.dto.user.UserCriteriaItems;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.exception.NotValidInformation;
import ir.hosseindn.mapper.customer.CustomerMapper;
import ir.hosseindn.mapper.technician.TechnicianMapper;
import ir.hosseindn.model.Admin;
import ir.hosseindn.model.Role;
import ir.hosseindn.repository.admin.AdminRepository;
import ir.hosseindn.service.customer.CustomerService;
import ir.hosseindn.service.technician.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final AdminRepository adminRepository;
    private final TechnicianService technicianService;
    private final CustomerService customerService;


    public Admin login(Admin admin) {
        return adminRepository.findByEmailAndPassword(admin.getEmail(), admin.getPassword()).orElseThrow(
                () -> new NotValidInformation("Email or Password is Incorrect")
        );
    }

    public List<Object> searchUsers(UserCriteriaItems request) {
        if(!(Arrays.stream(Role.values()).toList().contains(request.role()) || request.role()==null))
            throw new NotFoundException("Any user with this situations not found");
        List<Object> userList =new ArrayList<>();
        List<CustomerSaveResponse> customerSaveResponseList;
        List<TechnicianSaveResponse> technicianSaveResponseList;
        if (request.role() == null || request.role().equals(Role.ROLE_CUSTOMER)) {
            customerSaveResponseList = customerService.findByCriteria(request)
                    .stream()
                    .map(CustomerMapper.INSTANCE::modelToUserSaveResponse)
                    .toList();
            if (!customerSaveResponseList.isEmpty())
                userList.addAll(customerSaveResponseList);
        }
        if (request.role() == null || request.role().equals(Role.ROLE_TECHNICIAN)) {
            technicianSaveResponseList = technicianService.findByCriteria(request)
                    .stream()
                    .map(TechnicianMapper.INSTANCE::modelToUserSaveResponse)
                    .toList();
            if (!technicianSaveResponseList.isEmpty())
                userList.addAll(technicianSaveResponseList);
        }
        if(userList.isEmpty())
            throw new NotFoundException("Any user with this situations not found");
        return userList;
    }
}
