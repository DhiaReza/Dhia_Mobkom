package com.reza.kasir.ui.screen.coffeedrinks.mapper

import com.reza.kasir.data.CoffeeDrink
import com.reza.kasir.ui.screen.coffeedrinks.model.CoffeeDrinkItem

class CoffeeDrinkItemMapper {

    fun map(coffeeDrink: CoffeeDrink): CoffeeDrinkItem {
        return CoffeeDrinkItem(
            id = coffeeDrink.id,
            name = coffeeDrink.name,
            imageUrl = coffeeDrink.imageUrl,
            ingredients = coffeeDrink.ingredients,
            description = coffeeDrink.description,
            isFavourite = coffeeDrink.isFavourite
        )
    }
}
