
package com.genesis.data.repository

import com.genesis.data.remote.ProductService
import com.genesis.domain.model.Product
import com.genesis.domain.repository.ProductRepository

class ProductRepositoryImpl(
    private val productService: ProductService
) : ProductRepository {

    override suspend fun getProducts(): List<Product> {
        return productService.getProducts()
    }
}
