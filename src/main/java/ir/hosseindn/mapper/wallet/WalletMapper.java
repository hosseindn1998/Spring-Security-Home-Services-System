package ir.hosseindn.mapper.wallet;

import ir.hosseindn.dto.wallet.WalletId;
import ir.hosseindn.dto.wallet.WalletPayRequest;
import ir.hosseindn.dto.wallet.WalletPayResponse;
import ir.hosseindn.model.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface WalletMapper {
    WalletMapper INSTANCE = Mappers.getMapper(WalletMapper.class);

    Wallet walletIdToModel(WalletId walletId);

    WalletId modelToWalletId(Wallet wallet);

    Wallet walletPayRequestToModel(WalletPayRequest request);
    WalletPayResponse modelToWalletPayResponse(Wallet from);
}
