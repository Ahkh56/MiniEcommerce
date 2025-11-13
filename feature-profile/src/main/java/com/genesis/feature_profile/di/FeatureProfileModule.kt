package com.genesis.feature_profile.di

import com.genesis.feature_profile.viewmodel.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureProfileModule = module {
    viewModel {
        ProfileViewModel(get(), get())
    }
}
