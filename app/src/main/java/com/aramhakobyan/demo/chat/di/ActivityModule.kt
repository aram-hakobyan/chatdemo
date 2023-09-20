package com.aramhakobyan.demo.chat.di

import com.aramhakobyan.demo.chat.feature.chat.ChatAdapter
import com.aramhakobyan.demo.chat.feature.chat.NumberAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object ActivityModule {

    @ActivityRetainedScoped
    @Provides
    fun provideChatAdapter() = ChatAdapter(
        mutableListOf()
    )

    @ActivityRetainedScoped
    @Provides
    fun provideNumberAdapter() = NumberAdapter(30)
}
