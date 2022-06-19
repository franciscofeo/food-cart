package br.com.foodcart.gui

import br.com.foodcart.coreapi.CreateFoodCartCommand
import br.com.foodcart.coreapi.FindFoodCartQuery
import br.com.foodcart.coreapi.SelectProductCommand
import br.com.foodcart.gui.request.DeselectProductRequest
import br.com.foodcart.gui.request.SelectProductRequest
import br.com.foodcart.query.FoodCartView
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/foodcart")
class FoodCartController(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @PostMapping("/create")
    fun createFoodCart(): CompletableFuture<UUID> =
        commandGateway.send(CreateFoodCartCommand(UUID.randomUUID()))

    @PostMapping("/select")
    fun selectProduct(@RequestBody request: SelectProductRequest): CompletableFuture<UUID> =
        commandGateway.send(SelectProductCommand(request.foodCartId, request.productId, request.quantity))

    @PostMapping("/deselect")
    fun deselectProduct(@RequestBody request: DeselectProductRequest): CompletableFuture<UUID> =
        commandGateway.send(SelectProductCommand(request.foodCartId, request.productId, request.quantity))

    @GetMapping("/find/{foodCartId}")
    fun findFoodCart(@PathVariable foodCartId: UUID): CompletableFuture<FoodCartView> =
        queryGateway.query(
            FindFoodCartQuery(foodCartId),
            ResponseTypes.instanceOf(FoodCartView::class.java)
        )

}