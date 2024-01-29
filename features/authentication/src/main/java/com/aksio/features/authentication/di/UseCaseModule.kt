package com.aksio.features.authentication.di

import com.aksio.features.authentication.domain.email.ValidateEmailUseCase
import com.aksio.features.authentication.domain.email.ValidateEmailUseCaseImpl
import com.aksio.features.authentication.domain.password.ValidatePasswordUseCase
import com.aksio.features.authentication.domain.password.ValidatePasswordUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindValidateEmailUc(impl: ValidateEmailUseCaseImpl): ValidateEmailUseCase

    @Binds
    fun bindValidatePasswordUc(impl: ValidatePasswordUseCaseImpl): ValidatePasswordUseCase
}