package br.com.foodcart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FoodCartApplication

fun main(args: Array<String>) {
	runApplication<FoodCartApplication>(*args)
}
