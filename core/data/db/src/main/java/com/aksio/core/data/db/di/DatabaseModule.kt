package com.aksio.core.data.db.di

import android.content.Context
import androidx.room.Room
import com.aksio.core.data.db.db.ApplicationDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext appContext: Context
    ): ApplicationDb = Room
        .databaseBuilder(
            context = appContext,
            klass = ApplicationDb::class.java,
            name = "AuthenticationApp"
        )
        .build()

    @Provides
    @Singleton
    fun provideUserDao(db: ApplicationDb) = db.createUserDao()
}