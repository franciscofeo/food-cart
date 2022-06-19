package br.com.foodcart.gui.request

import java.util.UUID

data class SelectProductRequest(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)