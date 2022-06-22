package com.ameen.movies.domain.repository

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieGenre
import kotlinx.coroutines.flow.Flow

interface MoviesGenresRepository {
    fun getMovieGenres(): Flow<ResultWrapper<List<MovieGenre>>>
}