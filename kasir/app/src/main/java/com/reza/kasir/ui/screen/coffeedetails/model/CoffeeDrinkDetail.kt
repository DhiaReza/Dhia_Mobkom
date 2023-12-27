package com.reza.kasir.ui.screen.coffeedetails.model

data class CoffeeDrinkDetail(
    val id: Long,
    val name: String,
    val imageUrl: Int,
    val ingredients: String,
    val description: String,
    var isFavourite: Boolean
)
