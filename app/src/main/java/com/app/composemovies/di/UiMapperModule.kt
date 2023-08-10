package com.app.composemovies.di

import com.app.composemovies.ui.common.mapper.DomainToUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UiMapperModule {

    @Provides
    fun provideUiMapper(): DomainToUiMapper {
        return DomainToUiMapper()
    }
}