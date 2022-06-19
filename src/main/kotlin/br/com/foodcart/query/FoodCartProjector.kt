package br.com.foodcart.query

import br.com.foodcart.coreapi.FindFoodCartQuery
import br.com.foodcart.coreapi.FoodCartCreatedEvent
import br.com.foodcart.coreapi.ProductDeselectedEvent
import br.com.foodcart.coreapi.ProductSelectedEvent
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class FoodCartProjector(
    private val foodCartViewRepository: FoodCartViewRepository
) {

    @EventHandler
    fun on(event: FoodCartCreatedEvent) {
        val foodCart = FoodCartView(event.foodCartId, mutableMapOf())
        foodCartViewRepository.save(foodCart)
    }

    @EventHandler
    fun on(event: ProductSelectedEvent) {
        foodCartViewRepository.findById(event.foodCartId).ifPresent { foodCart ->
            foodCart.addProducts(event.productId, event.quantity)
        }
    }

    @EventHandler
    fun on(event: ProductDeselectedEvent) {
        foodCartViewRepository.findById(event.foodCartId).ifPresent { foodCart ->
            foodCart.removeProducts(foodCart.foodCartId, event.quantity)
        }
    }

    @QueryHandler
    fun handle(query: FindFoodCartQuery) = foodCartViewRepository.findById(query.foodCartId).orElse(null)

}