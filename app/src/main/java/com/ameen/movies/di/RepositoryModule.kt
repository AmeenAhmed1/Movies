package com.ameen.movies.di

import com.ameen.movies.data.mapper.MovieGenreDataMapper
import com.ameen.movies.data.remote.MoviesApi
import com.ameen.movies.data.repository.MoviesGenresRepositoryImp
import com.ameen.movies.data.repository.TopRatedMovieRepositoryImp
import com.ameen.movies.domain.repository.MoviesGenresRepository
import com.ameen.movies.domain.repository.TopRatedMovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesTopRatedMovieRepository(api: MoviesApi) =
        TopRatedMovieRepositoryImp(api) as TopRatedMovieRepository


    @Singleton
    @Provides
    fun providesMovieGenreRepository(api: MoviesApi, movieGenreDataMapper: MovieGenreDataMapper) =
        MoviesGenresRepositoryImp(api, movieGenreDataMapper) as MoviesGenresRepository

}