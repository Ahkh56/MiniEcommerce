
package com.genesis.domain.di

import com.genesis.domain.usecase.GetProductsUseCase
import org.koin.dsl.module

val domainModule = module {
    factory {
        GetProductsUseCase(get())
    }
}
