
package com.genesis.feature_cart.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.genesis.feature_cart.db.dao.CartDao
import com.genesis.feature_cart.db.entity.CartItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CartViewModel(private val cartDao: CartDao) : ViewModel() {

    val cartItems = cartDao.getCartItems()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addToCart(cartItem: CartItem) {
        viewModelScope.launch {
            val existingItem = cartDao.getCartItem(cartItem.id)
            if (existingItem != null) {
                val updatedItem = existingItem.copy(quantity = existingItem.quantity + 1)
                cartDao.update(updatedItem)
            } else {
                cartDao.insert(cartItem.copy(quantity = 1))
            }
        }
    }

    fun increaseQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            val updatedItem = cartItem.copy(quantity = cartItem.quantity + 1)
            cartDao.update(updatedItem)
        }
    }

    fun decreaseQuantity(cartItem: CartItem) {
        viewModelScope.launch {
            if (cartItem.quantity > 1) {
                val updatedItem = cartItem.copy(quantity = cartItem.quantity - 1)
                cartDao.update(updatedItem)
            } else {
                cartDao.delete(cartItem.id)
            }
        }
    }
}
