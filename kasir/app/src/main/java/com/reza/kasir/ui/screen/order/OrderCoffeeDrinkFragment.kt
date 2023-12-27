package com.reza.kasir.ui.screen.order

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.reza.kasir.R
import com.reza.kasir.ui.AppTheme
import com.reza.kasir.ui.Screen
import com.reza.kasir.ui.navigateToPreviousScreen
import com.reza.kasir.ui.state.UiState
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(ExperimentalAnimationApi::class)
class OrderCoffeeDrinkFragment : Fragment() {
    private val viewModel: OrderCoffeeDrinkViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            id = R.id.coffeeDrinkDetailsFragment
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            setContent {
                AppTheme {
                    viewModel.uiState.observeAsState(initial = UiState.Loading).value.let { uiState ->
                        when (uiState) {
                            is UiState.Loading -> {
                                ShowLoadingOrderCoffeeDrinksScreen()
                            }
                            is UiState.Success -> {
                                ShowSuccessOrderCoffeeDrinksScreen(
                                    orderCoffeeDrinkState = uiState.data,
                                    viewModel = viewModel,
                                    onBack = {
                                        this@OrderCoffeeDrinkFragment.navigateToPreviousScreen(
                                            Screen.OrderCoffeeDrinks,
                                            Screen.CoffeeDrinks
                                        )
                                    }
                                )
                            }
                            is UiState.Error -> {
                                ShowErrorOrderCoffeeDrinksScreen()
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadDrinks()
    }
}
