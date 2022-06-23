package com.ameen.movies.domain.repository

import com.ameen.movies.core.wrapper.ResultWrapper
import com.ameen.movies.domain.model.MovieData
import kotlinx.coroutines.flow.Flow

interface TopRatedMovieRepository {
    fun getTopRatedMovies(): Flow<ResultWrapper<List<MovieData>>>
}