package com.ameen.movies.di


import com.ameen.movies.data.mapper.DataModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    @Singleton
    fun provideMovieGenreMapper(): DataModelMapper = DataModelMapper()

}