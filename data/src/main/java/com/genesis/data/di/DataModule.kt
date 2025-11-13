
package com.genesis.data.di

import com.genesis.data.remote.FirebaseAuthDataSource
import com.genesis.data.remote.ProductService
import com.genesis.data.repository.AuthRepository
import com.genesis.data.repository.AuthRepositoryImpl
import com.genesis.data.repository.ProductRepositoryImpl
import com.genesis.data.repository.UserRepository
import com.genesis.data.repository.UserRepositoryImpl
import com.genesis.domain.repository.ProductRepository
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single {
        FirebaseAuthDataSource(get())
    }
    single {
        FirebaseFirestore.getInstance()
    }
    single<UserRepository> {
        UserRepositoryImpl(get())
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    single<ProductRepository> {
        ProductRepositoryImpl(get())
    }

    single<ProductService> {
        get<Retrofit>().create(ProductService::class.java)
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl("https://fakestoreapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
