package ir.hosseindn.mapper.wallet;

import ir.hosseindn.dto.order.OrderSaveResponse;
import ir.hosseindn.dto.wallet.WalletId;
import ir.hosseindn.dto.wallet.WalletPayRequest;
import ir.hosseindn.dto.wallet.WalletPayResponse;
import ir.hosseindn.model.Wallet;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-08T06:38:52+0330",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17.0.9 (Amazon.com Inc.)"
)
public class WalletMapperImpl implements WalletMapper {

    @Override
    public Wallet walletIdToModel(WalletId walletId) {
        if ( walletId == null ) {
            return null;
        }

        Wallet.WalletBuilder<?, ?> wallet = Wallet.builder();

        wallet.id( walletId.id() );

        return wallet.build();
    }

    @Override
    public WalletId modelToWalletId(Wallet wallet) {
        if ( wallet == null ) {
            return null;
        }

        Long id = null;

        id = wallet.getId();

        WalletId walletId = new WalletId( id );

        return walletId;
    }

    @Override
    public Wallet walletPayRequestToModel(WalletPayRequest request) {
        if ( request == null ) {
            return null;
        }

        Wallet.WalletBuilder<?, ?> wallet = Wallet.builder();

        return wallet.build();
    }

    @Override
    public WalletPayResponse modelToWalletPayResponse(Wallet from) {
        if ( from == null ) {
            return null;
        }

        OrderSaveResponse order = null;

        WalletPayResponse walletPayResponse = new WalletPayResponse( order );

        return walletPayResponse;
    }
}
