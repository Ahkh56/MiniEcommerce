
package com.genesis.composeauth.di

import com.genesis.core.di.coreModule
import com.genesis.data.di.dataModule
import com.genesis.domain.di.domainModule
import com.genesis.feature_auth.di.featureAuthModule
import com.genesis.feature_cart.di.featureCartModule
import com.genesis.feature_home.di.featureHomeModule
import com.genesis.feature_profile.di.featureProfileModule

val appModule = listOf(
    coreModule,
    dataModule,
    domainModule,
    featureAuthModule,
    featureCartModule,
    featureHomeModule,
    featureProfileModule
)
