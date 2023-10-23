package com.globa.prodmir.data.login.internal.token

import com.globa.prodmir.data.login.api.TokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface TokenRepositoryBinding {
    @Binds
    fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository
}