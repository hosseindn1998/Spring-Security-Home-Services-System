package ir.hosseindn.dto.wallet;

import ir.hosseindn.dto.order.OrderSaveResponse;

public record WalletPayResponse (
  OrderSaveResponse order
){
}
