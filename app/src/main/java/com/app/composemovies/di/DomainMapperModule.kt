package com.app.composemovies.di

import com.app.composemovies.data.mapper.DataToDomainMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DomainMapperModule {

    @Provides
    fun provideDataToDomainMapper(): DataToDomainMapper {
        return DataToDomainMapper()
    }
}