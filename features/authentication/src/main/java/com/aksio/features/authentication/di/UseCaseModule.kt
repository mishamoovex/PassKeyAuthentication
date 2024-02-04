package com.aksio.features.authentication.di

import com.aksio.features.authentication.domain.validation.StringValidationUseCase
import com.aksio.features.authentication.domain.validation.ValidateEmailUseCase
import com.aksio.features.authentication.domain.validation.ValidatePasswordUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Qualifier

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    @EmailValidator
    fun bindEmailValidator(impl: ValidateEmailUseCase): StringValidationUseCase

    @Binds
    @PasswordValidator
    fun bindPasswordValidator(impl: ValidatePasswordUseCase): StringValidationUseCase

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EmailValidator

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class PasswordValidator