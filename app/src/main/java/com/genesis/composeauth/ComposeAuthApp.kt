package com.genesis.composeauth

import android.app.Application
import com.genesis.composeauth.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ComposeAuthApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComposeAuthApp)
            modules(appModule)
        }
    }
}
