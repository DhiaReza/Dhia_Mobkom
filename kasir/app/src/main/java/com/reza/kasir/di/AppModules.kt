package com.reza.kasir.di

import com.reza.kasir.data.CoffeeDrinkDataSource
import com.reza.kasir.data.CoffeeDrinkRepository
import com.reza.kasir.data.DummyCoffeeDrinksDataSource
import com.reza.kasir.data.RuntimeCoffeeDrinkRepository
import com.reza.kasir.data.order.OrderCoffeeDrinksRepository
import com.reza.kasir.data.order.RuntimeOrderCoffeeDrinksRepository
import com.reza.kasir.ui.screen.coffeedetails.CoffeeDrinkDetailsViewModel
import com.reza.kasir.ui.screen.coffeedetails.mapper.CoffeeDrinkDetailMapper
import com.reza.kasir.ui.screen.coffeedrinks.CoffeeDrinksViewModel
import com.reza.kasir.ui.screen.coffeedrinks.mapper.CoffeeDrinkItemMapper
import com.reza.kasir.ui.screen.order.OrderCoffeeDrinkViewModel
import com.reza.kasir.ui.screen.order.mapper.OrderCoffeeDrinkMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dataModule = module {
    factory<CoffeeDrinkDataSource> { DummyCoffeeDrinksDataSource() }
    single<CoffeeDrinkRepository> { RuntimeCoffeeDrinkRepository }
    single<OrderCoffeeDrinksRepository> {
        RuntimeOrderCoffeeDrinksRepository(
            coffeeDrinkDataSource = get(),
            orderCoffeeDrinkMapper = get()
        )
    }
}

val mapperModule = module {
    factory { CoffeeDrinkItemMapper() }
    factory { CoffeeDrinkDetailMapper() }
    factory { OrderCoffeeDrinkMapper() }

    factory { com.reza.kasir.data.order.OrderCoffeeDrinkMapper() }
}

val viewModelModule = module {
    viewModel {
        OrderCoffeeDrinkViewModel(
            repository = get()
        )
    }
    viewModel {
        CoffeeDrinksViewModel(
            repository = get(),
            mapper = get()
        )
    }
    viewModel {
        CoffeeDrinkDetailsViewModel(
            repository = get(),
            mapper = get()
        )
    }
}
