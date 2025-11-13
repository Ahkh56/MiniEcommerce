
package com.genesis.domain.usecase

import com.genesis.domain.repository.ProductRepository

class GetProductsUseCase(private val productRepository: ProductRepository) {
    suspend operator fun invoke() = productRepository.getProducts()
}
