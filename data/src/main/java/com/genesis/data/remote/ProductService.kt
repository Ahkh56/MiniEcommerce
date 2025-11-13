
package com.genesis.data.remote

import com.genesis.domain.model.Product
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    suspend fun getProducts(): List<Product>
}
