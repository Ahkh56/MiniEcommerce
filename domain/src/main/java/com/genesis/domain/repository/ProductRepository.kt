
package com.genesis.domain.repository

import com.genesis.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}
