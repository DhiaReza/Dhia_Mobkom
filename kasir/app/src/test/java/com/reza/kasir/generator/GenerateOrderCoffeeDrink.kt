package com.reza.kasir.generator

import com.reza.kasir.data.order.OrderCoffeeDrink
import com.reza.kasir.generator.RandomData.randomDouble
import com.reza.kasir.generator.RandomData.randomInt
import com.reza.kasir.generator.RandomData.randomLong
import com.reza.kasir.generator.RandomData.randomString

object GenerateOrderCoffeeDrink {

    fun generateOrderCoffeeDrink(
        id: Long = randomLong(),
        name: String = randomString(),
        imageUrl: Int = randomInt(),
        ingredients: String = randomString(),
        price: Double = randomDouble(),
        count: Int = randomInt(99)
    ) = OrderCoffeeDrink(
        id = id,
        name = name,
        imageUrl = imageUrl,
        ingredients = ingredients,
        price = price,
        count = count
    )

    fun generateOrderCoffeeDrinks(
        count: Int = 10
    ): List<OrderCoffeeDrink> {
        val orderCoffeeDrinks = mutableListOf<OrderCoffeeDrink>()
        repeat(count) {
            orderCoffeeDrinks.add(generateOrderCoffeeDrink())
        }
        return orderCoffeeDrinks
    }
}
