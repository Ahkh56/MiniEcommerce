
package com.genesis.feature_cart.di

import androidx.room.Room
import com.genesis.feature_cart.db.CartDatabase
import com.genesis.feature_cart.vm.CartViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureCartModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            CartDatabase::class.java,
            "cart_database"
        ).build()
    }
    single { get<CartDatabase>().cartDao() }
    viewModel { CartViewModel(get()) }
}
