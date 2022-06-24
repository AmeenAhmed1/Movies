package com.ameen.movies.di

import com.ameen.movies.data.mapper.DataModelMapper
import com.ameen.movies.data.remote.MoviesApi
import com.ameen.movies.data.repository.MovieImagesRepositoryImp
import com.ameen.movies.data.repository.MoviesGenresRepositoryImp
import com.ameen.movies.data.repository.SearchMovieRepositoryImp
import com.ameen.movies.data.repository.TopRatedMovieRepositoryImp
import com.ameen.movies.domain.repository.MovieImagesRepository
import com.ameen.movies.domain.repository.MoviesGenresRepository
import com.ameen.movies.domain.repository.SearchMovieRepository
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
    fun providesTopRatedMovieRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        TopRatedMovieRepositoryImp(api, dataModelMapper) as TopRatedMovieRepository


    @Singleton
    @Provides
    fun providesMovieGenreRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        MoviesGenresRepositoryImp(api, dataModelMapper) as MoviesGenresRepository


    @Singleton
    @Provides
    fun providesMovieImagesRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        MovieImagesRepositoryImp(api, dataModelMapper) as MovieImagesRepository

    @Singleton
    @Provides
    fun providesSearchMoviesRepository(api: MoviesApi, dataModelMapper: DataModelMapper) =
        SearchMovieRepositoryImp(api, dataModelMapper) as SearchMovieRepository

}