package com.ameen.movies.di


import com.ameen.movies.data.mapper.MovieGenreDataMapper
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
    fun provideMovieGenreMapper(): MovieGenreDataMapper = MovieGenreDataMapper()

}