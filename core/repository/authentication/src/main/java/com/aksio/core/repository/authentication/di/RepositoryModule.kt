package com.aksio.core.repository.authentication.di

import com.aksio.core.repository.authentication.repository.AuthenticationRepository
import com.aksio.core.repository.authentication.repository.AuthenticationRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

    @Binds
    @Singleton
    fun bindRepository(impl: AuthenticationRepositoryImpl): AuthenticationRepository
}