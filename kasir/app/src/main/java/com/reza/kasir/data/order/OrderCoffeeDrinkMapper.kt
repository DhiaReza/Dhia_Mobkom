package com.reza.kasir.data.order

import com.reza.kasir.data.CoffeeDrink

class OrderCoffeeDrinkMapper {

    fun map(coffeeDrink: CoffeeDrink, count: Int = 0): OrderCoffeeDrink {
        return OrderCoffeeDrink(
            id = coffeeDrink.id,
            name = coffeeDrink.name,
            imageUrl = coffeeDrink.imageUrl,
            ingredients = coffeeDrink.ingredients,
            price = coffeeDrink.price,
            count = count
        )
    }

    fun map(coffeeDrinks: List<CoffeeDrink>, count: Int = 0): List<OrderCoffeeDrink> {
        return coffeeDrinks.map { map(it, count) }
    }
}
