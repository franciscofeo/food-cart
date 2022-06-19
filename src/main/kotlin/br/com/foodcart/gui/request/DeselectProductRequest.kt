package br.com.foodcart.gui.request

import java.util.UUID

data class DeselectProductRequest(
    val foodCartId: UUID,
    val productId: UUID,
    val quantity: Int
)