package br.com.foodcart.command

import br.com.foodcart.coreapi.ConfirmOrderCommand
import br.com.foodcart.coreapi.CreateFoodCartCommand
import br.com.foodcart.coreapi.DeselectProductCommand
import br.com.foodcart.coreapi.FoodCartCreatedEvent
import br.com.foodcart.coreapi.OrderConfirmedEvent
import br.com.foodcart.coreapi.ProductDeselectedEvent
import br.com.foodcart.coreapi.ProductSelectedEvent
import br.com.foodcart.coreapi.SelectProductCommand
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import org.slf4j.LoggerFactory
import java.util.UUID


@Aggregate
class FoodCart() {

    @AggregateIdentifier
    private lateinit var foodCartId: UUID
    private lateinit var selectedProducts: HashMap<UUID, Int>
    private var confirmed: Boolean = false

    companion object {
        private val logger = LoggerFactory.getLogger(FoodCart::class.java)
    }

    @CommandHandler
    constructor(command: CreateFoodCartCommand) : this() {
        AggregateLifecycle.apply(FoodCartCreatedEvent(foodCartId = command.foodCartId))
    }

    @CommandHandler
    fun handle(command: SelectProductCommand) {
        AggregateLifecycle.apply(
            ProductSelectedEvent(
                foodCartId = this.foodCartId,
                productId = command.productId,
                quantity = command.quantity
            )
        )
    }

    @CommandHandler
    fun handle(command: DeselectProductCommand) {
        AggregateLifecycle.apply(
            ProductDeselectedEvent(
                foodCartId = this.foodCartId,
                productId = command.productId,
                quantity = command.quantity
            )
        )
    }

    @CommandHandler
    fun handle(command: ConfirmOrderCommand) {
        AggregateLifecycle.apply(
            OrderConfirmedEvent(
                foodCartId = this.foodCartId
            )
        )
    }

    @EventSourcingHandler
    fun on(event: FoodCartCreatedEvent){
        foodCartId = event.foodCartId
        selectedProducts = HashMap()
        confirmed = false
    }

    @EventSourcingHandler
    fun on(event: ProductSelectedEvent){
        // https://www.programiz.com/java-programming/library/hashmap/merge
        selectedProducts.merge(event.productId, event.quantity, Integer::sum)
    }

    @EventSourcingHandler
    fun on(event: ProductDeselectedEvent){
        // https://www.techiedelight.com/increment-value-map-kotlin/
        selectedProducts.computeIfPresent(
            event.productId
        ) { _, quantity -> event.quantity - quantity}
    }

    @EventSourcingHandler
    fun on(event: OrderConfirmedEvent){
        this.confirmed = true
    }


}