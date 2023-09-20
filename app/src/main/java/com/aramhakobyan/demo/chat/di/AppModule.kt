package com.aramhakobyan.demo.chat.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @DefaultScope
    @Provides
    fun provideDefaultScope(
        @DefaultDispatcher coroutineDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + coroutineDispatcher)
    }

    @Singleton
    @IOScope
    @Provides
    fun provideIOScope(
        @IoDispatcher coroutineDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + coroutineDispatcher)
    }

    @Singleton
    @MainScope
    @Provides
    fun provideMainScope(
        @MainDispatcher coroutineDispatcher: CoroutineDispatcher
    ): CoroutineScope {
        return CoroutineScope(SupervisorJob() + coroutineDispatcher)
    }
}

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class DefaultScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class IOScope

@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class MainScope
