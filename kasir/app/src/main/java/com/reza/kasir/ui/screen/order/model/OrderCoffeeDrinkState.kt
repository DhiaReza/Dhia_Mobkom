package com.reza.kasir.ui.screen.order.model

import com.reza.kasir.data.order.OrderCoffeeDrink
import java.math.BigDecimal

data class OrderCoffeeDrinkState(
    val coffeeDrinks: List<OrderCoffeeDrink>,
    val totalPrice: BigDecimal
)
