package com.globa.prodmir.data.login.internal.login

import com.globa.prodmir.data.login.api.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface LoginRepositoryBinding {
    @Binds
    fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}