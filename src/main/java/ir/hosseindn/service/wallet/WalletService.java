package ir.hosseindn.service.wallet;

import ir.hosseindn.exception.NotEnoughAccountBalanceException;
import ir.hosseindn.exception.NotFoundException;
import ir.hosseindn.model.Wallet;
import ir.hosseindn.repository.wallet.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public void payFromWallet(Wallet customerWallet, Wallet technicianWallet, Long amount) {
        Wallet foundedCustomerWallet = walletRepository.findById(customerWallet.getId()).orElseThrow(
                () -> new NotFoundException(String.format("Customer wallet by id %s not found", customerWallet.getId()))
        );
        if (foundedCustomerWallet.getAmount() < amount)
            throw new NotEnoughAccountBalanceException("Customer's account balance is insufficient");
        walletRepository.withdraw(customerWallet.getId(), amount);
        walletRepository.deposit(technicianWallet.getId(), Math.round(amount * 0.7));
        walletRepository.deposit(1L, amount - Math.round(amount * 0.7));
    }

    public void deposit(Long walletId, Long amount) {
        walletRepository.deposit(walletId, amount);
    }

}
