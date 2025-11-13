package com.genesis.core.di

import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val coreModule = module {
    single {
        FirebaseAuth.getInstance()
    }
}
