package br.com.foodcart.query

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID
import javax.persistence.ElementCollection
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id

@Entity
open class FoodCartView(
    @Id
    open val foodCartId: UUID,
    @ElementCollection(fetch = FetchType.EAGER)
    open val products: MutableMap<UUID, Int>
) {

    fun addProducts(productId: UUID, amount: Int) =
        products.compute(productId) { _, quantity -> (quantity ?: 0) + amount }

    fun removeProducts(productId: UUID, amount: Int) {
        val productQuantiyUpdated = products.compute(productId) { _, quantity -> (quantity ?: 0) - amount }
        if(productQuantiyUpdated == 0){
            products.remove(productId)
        }

    }
}

@Repository
interface FoodCartViewRepository : JpaRepository<FoodCartView, UUID>