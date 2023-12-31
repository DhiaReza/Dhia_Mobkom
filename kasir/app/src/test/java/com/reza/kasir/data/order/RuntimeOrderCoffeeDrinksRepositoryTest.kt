package com.reza.kasir.data.order

import com.reza.kasir.data.CoffeeDrink
import com.reza.kasir.data.CoffeeDrinkDataSource
import com.reza.kasir.generator.GenerateCoffeeDrink.generateCoffeeDrink
import com.reza.kasir.generator.GenerateCoffeeDrink.generateCoffeeDrinks
import com.reza.kasir.generator.GenerateOrderCoffeeDrink.generateOrderCoffeeDrink
import com.reza.kasir.generator.GenerateOrderCoffeeDrink.generateOrderCoffeeDrinks
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class RuntimeOrderCoffeeDrinksRepositoryTest {
    private val coffeeDrinkDataSource = mockk<CoffeeDrinkDataSource>()
    private val orderCoffeeDrinkMapper = mockk<OrderCoffeeDrinkMapper>()

    private val repository = RuntimeOrderCoffeeDrinksRepository(
        coffeeDrinkDataSource,
        orderCoffeeDrinkMapper
    )

    @Test
    fun should_returnCoffeeDrinks_when_dataSourceReturnData() {
        val coffeeDrinks = generateCoffeeDrinks(count = 10)
        val orderCoffeeDrinks = generateOrderCoffeeDrinks(count = 10)

        stubGetCoffeeDrinks(coffeeDrinks)
        stubMapToOrderCoffeeDrink(coffeeDrinks, orderCoffeeDrinks)

        runBlocking {
            assertEquals(
                orderCoffeeDrinks,
                repository.getCoffeeDrinks().single()
            )
        }
    }

    @Test
    fun should_addMethod_updateCoffeeDrinksList_when_countIsLessThanMaximum() {
        val coffeeDrinks = listOf(generateCoffeeDrink())
        val orderCoffeeDrinks = listOf(generateOrderCoffeeDrink(count = 0))
        val updateOrderCoffeeDrinks = listOf(orderCoffeeDrinks.first().copy(count = 1))

        stubGetCoffeeDrinks(coffeeDrinks)
        stubMapToOrderCoffeeDrink(coffeeDrinks, orderCoffeeDrinks)

        runBlocking {
            repository.getCoffeeDrinks().single()
            repository.add(orderCoffeeDrinks.first().id).single()
            assertEquals(
                updateOrderCoffeeDrinks,
                repository.getCoffeeDrinks().single()
            )
        }
    }

    @Test
    fun should_addMethod_notUpdateCoffeeDrinksList_when_countIsEqualToMaximum() {
        val coffeeDrinks = listOf(generateCoffeeDrink())
        val orderCoffeeDrinks = listOf(generateOrderCoffeeDrink(count = 99))

        stubGetCoffeeDrinks(coffeeDrinks)
        stubMapToOrderCoffeeDrink(coffeeDrinks, orderCoffeeDrinks)

        runBlocking {
            repository.getCoffeeDrinks().single()
            repository.add(orderCoffeeDrinks.first().id).single()
            assertEquals(
                orderCoffeeDrinks,
                repository.getCoffeeDrinks().single()
            )
        }
    }

    @Test
    fun should_removeMethod_updateCoffeeDrinksList_when_countIsMoreThanMinimum() {
        val coffeeDrinks = listOf(generateCoffeeDrink())
        val orderCoffeeDrinks = listOf(generateOrderCoffeeDrink(count = 42))
        val updateOrderCoffeeDrinks = listOf(orderCoffeeDrinks.first().copy(count = 41))

        stubGetCoffeeDrinks(coffeeDrinks)
        stubMapToOrderCoffeeDrink(coffeeDrinks, orderCoffeeDrinks)

        runBlocking {
            repository.getCoffeeDrinks().single()
            repository.remove(orderCoffeeDrinks.first().id).single()
            assertEquals(
                updateOrderCoffeeDrinks,
                repository.getCoffeeDrinks().single()
            )
        }
    }

    @Test
    fun should_removeMethod_notUpdateCoffeeDrinksList_when_countIsEqualToMinimum() {
        val coffeeDrinks = listOf(generateCoffeeDrink())
        val orderCoffeeDrinks = listOf(generateOrderCoffeeDrink(count = 0))

        stubGetCoffeeDrinks(coffeeDrinks)
        stubMapToOrderCoffeeDrink(coffeeDrinks, orderCoffeeDrinks)

        runBlocking {
            repository.getCoffeeDrinks().single()
            repository.remove(orderCoffeeDrinks.first().id).single()
            assertEquals(
                orderCoffeeDrinks,
                repository.getCoffeeDrinks().single()
            )
        }
    }

    private fun stubGetCoffeeDrinks(coffeeDrinks: List<CoffeeDrink>) {
        every { coffeeDrinkDataSource.getCoffeeDrinks() } returns coffeeDrinks
    }

    private fun stubMapToOrderCoffeeDrink(
        coffeeDrinks: List<CoffeeDrink>,
        orderCoffeeDrink: List<OrderCoffeeDrink>
    ) {
        every { orderCoffeeDrinkMapper.map(coffeeDrinks) } returns orderCoffeeDrink
    }
}
