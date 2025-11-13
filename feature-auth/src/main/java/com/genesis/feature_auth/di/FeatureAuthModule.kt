package com.genesis.feature_auth.di

import com.genesis.core.navigation.NavigationProvider
import com.genesis.feature_auth.navigation.AuthNavigationProvider
import com.genesis.feature_auth.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val featureAuthModule = module {
    viewModel {
        AuthViewModel(get())
    }
    singleOf(::AuthNavigationProvider).bind<NavigationProvider>()
}
