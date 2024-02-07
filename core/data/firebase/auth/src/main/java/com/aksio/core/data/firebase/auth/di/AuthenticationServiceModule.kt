package com.aksio.core.data.firebase.auth.di

import com.aksio.core.data.firebase.auth.service.AuthenticationService
import com.aksio.core.data.firebase.auth.service.FirebaseAuthenticationService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AuthenticationServiceModule {

    @Binds
    @Singleton
    fun bindAuthService(impl: FirebaseAuthenticationService): AuthenticationService
}