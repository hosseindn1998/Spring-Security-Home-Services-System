package ir.hosseindn.dto.wallet;

import ir.hosseindn.dto.order.OrderId;

public record WalletPayRequest(
OrderId order
) {
}
