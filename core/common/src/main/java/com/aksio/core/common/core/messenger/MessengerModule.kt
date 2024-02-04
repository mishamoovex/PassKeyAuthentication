package com.aksio.core.common.core.messenger

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.aksio.core.common.core.messenger.base.BaseMessenger
import com.aksio.core.common.core.messenger.base.Messenger
import com.aksio.core.common.core.messenger.error.ErrorMessenger
import com.aksio.core.common.core.messenger.error.ErrorMessengerImpl

@Module
@InstallIn(ViewModelComponent::class)
interface MessengerModule {

    @Binds
    fun bindBaseMessenger(impl: BaseMessenger): Messenger

    @Binds
    fun bindErrorMessenger(impl: ErrorMessengerImpl): ErrorMessenger
}