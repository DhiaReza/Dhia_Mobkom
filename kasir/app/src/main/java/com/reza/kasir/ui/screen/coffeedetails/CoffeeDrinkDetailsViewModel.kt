package com.reza.kasir.ui.screen.coffeedetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.reza.kasir.data.CoffeeDrinkRepository
import com.reza.kasir.ui.screen.coffeedetails.exception.NoCoffeeDrinkFoundException
import com.reza.kasir.ui.screen.coffeedetails.mapper.CoffeeDrinkDetailMapper
import com.reza.kasir.ui.screen.coffeedetails.model.CoffeeDrinkDetail
import com.reza.kasir.ui.screen.coffeedetails.model.CoffeeDrinkDetailState
import com.reza.kasir.ui.state.UiState
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class CoffeeDrinkDetailsViewModel(
    private val repository: CoffeeDrinkRepository,
    private val mapper: CoffeeDrinkDetailMapper
) : ViewModel() {
    private val _uiState: MutableLiveData<UiState<CoffeeDrinkDetailState>> = MutableLiveData()
    val uiState: LiveData<UiState<CoffeeDrinkDetailState>>
        get() = _uiState

    fun loadCoffeeDrinkDetails(coffeeDrinkId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getCoffeeDrink(coffeeDrinkId)
                .map { mapper.map(it) }
                .collect { coffeeDrink ->
                    if (coffeeDrink != null) {
                        _uiState.value = UiState.Success(CoffeeDrinkDetailState(coffeeDrink))
                    } else {
                        _uiState.value = UiState.Error(NoCoffeeDrinkFoundException())
                    }
                }
        }
    }

    fun changeFavouriteState(coffeeDrink: CoffeeDrinkDetail) {
        viewModelScope.launch {
            repository.updateFavouriteState(coffeeDrink.id, !coffeeDrink.isFavourite)
                .collect { result ->
                    if (result) {
                        loadCoffeeDrinkDetails(coffeeDrink.id)
                    }
                }
        }
    }
}
