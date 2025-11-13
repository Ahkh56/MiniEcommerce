package com.genesis.feature_home.ui

import com.genesis.domain.model.Product

sealed interface HomeUiState {
    object Loading : HomeUiState
    data class Success(val products: List<Product>) : HomeUiState
    data class Error(val message: String) : HomeUiState
}