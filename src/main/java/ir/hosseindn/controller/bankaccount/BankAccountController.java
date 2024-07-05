package ir.hosseindn.controller.bankaccount;

import ir.hosseindn.dto.bankaccount.BankAccountSaveRequest;
import ir.hosseindn.mapper.bankaccount.BankAccountMapper;
import ir.hosseindn.model.BankAccount;
import ir.hosseindn.service.bankaccount.BankAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class BankAccountController {
    private final BankAccountService bankAccountService;
    @PostMapping("add-bank-account")
    public BankAccount save(@Valid @RequestBody BankAccountSaveRequest request){
        BankAccount mappedBankAccount= BankAccountMapper.INSTANCE.bankAccountSaveRequestToModel(request);
        return bankAccountService.save(mappedBankAccount);
    }
}
