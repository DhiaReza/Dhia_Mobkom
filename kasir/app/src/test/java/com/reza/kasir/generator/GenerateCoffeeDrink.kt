package com.reza.kasir.generator

import com.reza.kasir.data.CoffeeDrink
import com.reza.kasir.generator.RandomData.randomBoolean
import com.reza.kasir.generator.RandomData.randomDouble
import com.reza.kasir.generator.RandomData.randomInt
import com.reza.kasir.generator.RandomData.randomLong
import com.reza.kasir.generator.RandomData.randomString

object GenerateCoffeeDrink {

    fun generateCoffeeDrink(
        id: Long = randomLong(),
        name: String = randomString(),
        imageUrl: Int = randomInt(),
        description: String = randomString(),
        ingredients: String = randomString(),
        orderDescription: String = randomString(),
        price: Double = randomDouble(),
        isFavourite: Boolean = randomBoolean()
    ) = CoffeeDrink(
        id = id,
        name = name,
        imageUrl = imageUrl,
        description = description,
        ingredients = ingredients,
        orderDescription = orderDescription,
        price = price,
        isFavourite = isFavourite
    )

    fun generateCoffeeDrinks(
        count: Int = 10
    ): List<CoffeeDrink> {
        val coffeeDrinks = mutableListOf<CoffeeDrink>()
        repeat(count) {
            coffeeDrinks.add(generateCoffeeDrink())
        }
        return coffeeDrinks
    }
}
